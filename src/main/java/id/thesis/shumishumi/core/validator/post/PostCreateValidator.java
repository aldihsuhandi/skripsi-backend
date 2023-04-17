package id.thesis.shumishumi.core.validator.post;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.post.PostCreateRequest;
import id.thesis.shumishumi.core.validator.BaseValidator;

public class PostCreateValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof PostCreateRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        PostCreateRequest request = (PostCreateRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getTitle(), "title", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isNotEmpty(request.getTags(), "tags", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
