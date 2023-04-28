package id.thesis.shumishumi.core.validator.comment;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.comment.UpvoteCommentRequest;

public class CommentUpvoteValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof UpvoteCommentRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        UpvoteCommentRequest request = (UpvoteCommentRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getCommentId(), "commentId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
