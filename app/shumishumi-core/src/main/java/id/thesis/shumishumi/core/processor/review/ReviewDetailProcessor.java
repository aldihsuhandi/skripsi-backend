package id.thesis.shumishumi.core.processor.review;

import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.ReviewService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.converter.SummaryConverter;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.model.viewobject.ReviewVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.review.ReviewDetailRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.review.ReviewDetailResult;
import org.springframework.beans.factory.annotation.Autowired;

public class ReviewDetailProcessor implements BaseProcessor {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ItemService itemService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        ReviewDetailRequest request = (ReviewDetailRequest) baseRequest;
        ReviewDetailResult result = (ReviewDetailResult) baseResult;

        String userId = sessionService.query(request.getSessionId()).getUserId();

        ReviewVO review = reviewService.queryById(request.getReviewId());
        AssertUtil.isNotNull(review, "Review is not exist", ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        AssertUtil.isExpected(review.getUserId(), userId, "user is not expected", ShumishumiErrorCodeEnum.USER_INVALID);

        ItemVO item = itemService.queryById(review.getItemId(), true);

        result.setReview(SummaryConverter.toSummary(review));
        result.setItem(SummaryConverter.toSummary(item));
    }
}
