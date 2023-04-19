package id.thesis.shumishumi.core.validator.post;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.post.PostEditRequest;

public class PostEditValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof PostEditRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        PostEditRequest request = (PostEditRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getPostId(), "postId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
