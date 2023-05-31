package id.thesis.shumishumi.core.validator.review;

import id.thesis.shumishumi.common.service.InterestLevelService;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.review.ReviewCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class ReviewCreateValidator implements BaseValidator {

    @Autowired
    private InterestLevelService interestLevelService;

    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof ReviewCreateRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ReviewCreateRequest request = (ReviewCreateRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getReviewId(), "reviewId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ParamChecker.isNotEmpty(request.getInterestLevel(), "interestLevel",
                ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isNotNull(interestLevelService.query(request.getInterestLevel(), DatabaseConst.INTEREST_LEVEL_NAME),
                "interestLevel", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ParamChecker.isExpected(request.getReview() >= 1 && request.getReview() <= 5, "review",
                ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
