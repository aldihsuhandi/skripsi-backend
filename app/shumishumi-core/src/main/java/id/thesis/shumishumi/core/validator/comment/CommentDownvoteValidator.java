package id.thesis.shumishumi.core.validator.comment;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.comment.DownvoteCommentRequest;

public class CommentDownvoteValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof DownvoteCommentRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        DownvoteCommentRequest request = (DownvoteCommentRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getCommentId(), "commentId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
