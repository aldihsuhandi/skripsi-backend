package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.ReviewDO;
import id.thesis.shumishumi.foundation.repository.ReviewRepository;
import id.thesis.shumishumi.foundation.service.ReviewDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewDAOImpl implements ReviewDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<ReviewDO> queryByMerchant(String merchantId, PagingContext pagingContext) {
        LogUtil.info(LOGGER, String.format("ReviewDAO#queryByMerchant[merchantId=%s,pagingContext=%s]", merchantId, pagingContext));
        List<ReviewDO> reviews;
        try {
            Pageable pageable = PageRequest.of(pagingContext.getPageNumber() - 1, pagingContext.getNumberOfItem());
            Page<ReviewDO> reviewDOPage = reviewRepository.findByMerchantIdAndNeedReview(merchantId, false, pageable);

            pagingContext.setTotalItem(reviewDOPage.getTotalElements());
            reviews = reviewDOPage.getContent();
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("ReviewDAO#queryByMerchant[result=%s]", reviews));
        return reviews;
    }

    @Override
    public List<ReviewDO> queryByUserIdAndNeedReview(String userId, boolean needReview, PagingContext pagingContext) {
        LogUtil.info(LOGGER, String.format("ReviewDAO#queryByUserIdAndNeedReview[userId=%s,needReview=%s,pagingContext=%s]",
                userId, needReview, pagingContext));
        List<ReviewDO> reviews;
        try {
            Pageable pageable = PageRequest.of(pagingContext.getPageNumber() - 1, pagingContext.getNumberOfItem());
            Page<ReviewDO> reviewDOPage = reviewRepository.findByUserIdAndNeedReview(userId, needReview, pageable);

            pagingContext.setTotalItem(reviewDOPage.getTotalElements());
            reviews = reviewDOPage.getContent();
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("ReviewDAO#queryByUserIdAndNeedReview[result=%s]", reviews));
        return reviews;
    }

    @Override
    public List<ReviewDO> queryByItemId(String itemId) {
        LogUtil.info(LOGGER, String.format("ReviewDAO#queryByItemId[itemId=%s]", itemId));
        List<ReviewDO> reviews;
        try {
            reviews = reviewRepository.findByItemIdAndNeedReview(itemId, false);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("ReviewDAO#queryByItemId[reviews=%s]", reviews));
        return reviews;
    }

    @Override
    public ReviewDO queryById(String reviewId) {
        LogUtil.info(LOGGER, String.format("ReviewDAO#queryById[reviewId=%s]", reviewId));

        ReviewDO reviewDO;
        try {
            reviewDO = reviewRepository.findById(reviewId).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("ReviewDAO#queryById[review=%s]", reviewDO));
        return reviewDO;
    }

    @Override
    public void create(ReviewDO reviewDO) {
        LogUtil.info(LOGGER, String.format("ReviewDAO#create[reviewDO=%s]", reviewDO));
        try {
            reviewRepository.save(reviewDO);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void update(ReviewDO reviewDO) {
        LogUtil.info(LOGGER, String.format("ReviewDAO#update[reviewDO=%s]", reviewDO));
        try {
            reviewRepository.save(reviewDO);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }
}
