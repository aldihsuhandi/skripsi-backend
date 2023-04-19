package id.thesis.shumishumi.core.validator.post;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.post.PostQueryRequest;

public class PostQueryValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof PostQueryRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        PostQueryRequest request = (PostQueryRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getPostId(), "postId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
