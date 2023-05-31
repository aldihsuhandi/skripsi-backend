package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.service.InterestLevelService;
import id.thesis.shumishumi.common.service.ReviewService;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.viewobject.InterestLevelVO;
import id.thesis.shumishumi.facade.model.viewobject.ReviewVO;
import id.thesis.shumishumi.foundation.model.result.ReviewDO;
import id.thesis.shumishumi.foundation.service.ReviewDAO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    @Autowired
    private InterestLevelService interestLevelService;

    @Override
    public void create(ReviewVO review) {
        review.setReviewId(FunctionUtil.generateUUID());

        ReviewDO reviewDO = convertToDataObject(review);
        reviewDAO.create(reviewDO);
    }

    @Override
    public void update(ReviewVO review) {
        ReviewDO reviewDO = convertToDataObject(review);
        reviewDAO.update(reviewDO);
    }

    @Override
    public ReviewVO queryById(String id) {
        ReviewDO reviewDO = reviewDAO.queryById(id);
        if (reviewDO == null) {
            return null;
        }

        return convertToVO(reviewDO);
    }

    @Override
    public List<ReviewVO> queryByMerchant(String merchantId, PagingContext pagingContext) {
        return reviewDAO.queryByMerchant(merchantId, pagingContext).stream()
                .map(this::convertToVO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewVO> queryByUserAndNeedReview(String userId, boolean needReview, PagingContext pagingContext) {
        return reviewDAO.queryByUserIdAndNeedReview(userId, needReview, pagingContext).stream()
                .map(this::convertToVO).collect(Collectors.toList());
    }

    private ReviewDO convertToDataObject(ReviewVO review) {
        if (review == null) {
            return null;
        }

        StringBuilder imageBuilder = new StringBuilder();
        if (!CollectionUtils.isEmpty(review.getReviewImages())) {
            review.getReviewImages().forEach(image ->
                    imageBuilder.append(image).append(CommonConst.SEPARATOR));
        }
        String imageStr = "";
        if (imageBuilder.length() != 0) {
            imageStr = imageBuilder.substring(0, imageBuilder.length() - 1);
        }

        ReviewDO reviewDO = new ReviewDO();
        reviewDO.setReviewId(review.getReviewId());
        reviewDO.setItemId(review.getItemId());
        reviewDO.setUserId(review.getUserId());
        reviewDO.setMerchantId(review.getMerchantId());
        reviewDO.setDescription(review.getDescription());
        reviewDO.setReviewImages(imageStr);
        if (reviewDO.getInterestLevel() != null) {
            reviewDO.setInterestLevel(review.getInterestLevel().getInterestLevelId());
        }
        reviewDO.setStar(review.getStar());
        reviewDO.setNeedReview(review.isNeedReview());

        return reviewDO;
    }

    private ReviewVO convertToVO(ReviewDO reviewDO) {
        if (reviewDO == null) {
            return null;
        }

        InterestLevelVO levelVO = new InterestLevelVO();
        levelVO.setInterestLevelId(reviewDO.getInterestLevel());

        ReviewVO vo = new ReviewVO();
        vo.setReviewId(reviewDO.getReviewId());
        vo.setItemId(reviewDO.getItemId());
        vo.setUserId(reviewDO.getUserId());
        vo.setMerchantId(reviewDO.getMerchantId());
        vo.setDescription(reviewDO.getDescription());
        vo.setInterestLevel(levelVO);
        vo.setNeedReview(reviewDO.getNeedReview());
        vo.setStar(reviewDO.getStar());

        if (StringUtils.isNotEmpty(reviewDO.getReviewImages())) {
            vo.setReviewImages(Arrays.asList(reviewDO.getReviewImages()
                    .split(CommonConst.SEPARATOR_SPLIT)));
        }

        return vo;
    }

    private void composeInterestLevel(ReviewVO reviewVO) {
        if (reviewVO == null) {
            return;
        }

        InterestLevelVO level = interestLevelService.query(reviewVO.
                getInterestLevel().getInterestLevelId(), DatabaseConst.INTEREST_LEVEL_ID);
        reviewVO.setInterestLevel(level);
    }
}
