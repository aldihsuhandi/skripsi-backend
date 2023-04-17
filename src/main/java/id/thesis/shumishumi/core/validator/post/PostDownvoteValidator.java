package id.thesis.shumishumi.core.validator.post;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.post.PostDownvoteRequest;
import id.thesis.shumishumi.core.validator.BaseValidator;

public class PostDownvoteValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof PostDownvoteRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        PostDownvoteRequest request = (PostDownvoteRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getPostId(), "postId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
