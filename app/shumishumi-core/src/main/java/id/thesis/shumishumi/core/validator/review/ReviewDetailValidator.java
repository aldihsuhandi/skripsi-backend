package id.thesis.shumishumi.core.validator.review;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.review.ReviewDetailRequest;

public class ReviewDetailValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof ReviewDetailRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ReviewDetailRequest request = (ReviewDetailRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getReviewId(), "reviewId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
