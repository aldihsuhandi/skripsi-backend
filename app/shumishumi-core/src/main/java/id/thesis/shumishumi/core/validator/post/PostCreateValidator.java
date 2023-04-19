package id.thesis.shumishumi.core.validator.post;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.post.PostCreateRequest;

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
