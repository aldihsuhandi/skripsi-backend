package id.thesis.shumishumi.core.validator.post;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.post.PostDeleteRequest;

public class PostDeleteValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof PostDeleteRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        PostDeleteRequest request = (PostDeleteRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getPostId(), "postId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
