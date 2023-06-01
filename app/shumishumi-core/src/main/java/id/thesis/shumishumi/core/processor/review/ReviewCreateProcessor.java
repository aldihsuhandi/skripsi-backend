package id.thesis.shumishumi.core.processor.review;

import id.thesis.shumishumi.common.service.InterestLevelService;
import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.ReviewService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.InterestLevelVO;
import id.thesis.shumishumi.facade.model.viewobject.ReviewVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.review.ReviewCreateRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class ReviewCreateProcessor implements BaseProcessor {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private InterestLevelService interestLevelService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ItemService itemService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        ReviewCreateRequest request = (ReviewCreateRequest) baseRequest;

        String userId = sessionService.query(request.getSessionId()).getUserId();

        ReviewVO review = reviewService.queryById(request.getReviewId());
        AssertUtil.isNotNull(review, "review not found", ShumishumiErrorCodeEnum.REVIEW_NOT_FOUND);
        AssertUtil.isExpected(StringUtils.equals(userId, review.getUserId()), "user is not expected", ShumishumiErrorCodeEnum.USER_INVALID);

        InterestLevelVO levelVO = interestLevelService.query(request.getInterestLevel(),
                DatabaseConst.INTEREST_LEVEL_NAME);

        review.setStar(request.getReview());
        review.setInterestLevel(levelVO);
        review.setDescription(request.getDescription());
        review.setReviewImages(request.getImages());
        review.setNeedReview(false);

        reviewService.update(review);
        itemService.calculateUserReview(review.getItemId());
    }
}
