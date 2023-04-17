package id.thesis.shumishumi.core.validator.post;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.post.PostUpvoteRequest;
import id.thesis.shumishumi.core.validator.BaseValidator;

public class PostUpvoteValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof PostUpvoteRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        PostUpvoteRequest request = (PostUpvoteRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getPostId(), "postId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
