package id.thesis.shumishumi.core.validator.review;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.review.ReviewQueryRequest;
import org.apache.commons.lang3.StringUtils;

public class ReviewQueryValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof ReviewQueryRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ReviewQueryRequest request = (ReviewQueryRequest) baseRequest;
        ParamChecker.isExpected(StringUtils.equals(request.getType(), "MERCHANT") ||
                StringUtils.equals(request.getType(), "USER"), "type", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        if (StringUtils.equals(request.getType(), "MERCHANT")) {
            ParamChecker.isNotEmpty(request.getMerchantName(), "merchantName", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
            ParamChecker.isExpected(!request.isNeedReview(), "needReview", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        }
    }
}
