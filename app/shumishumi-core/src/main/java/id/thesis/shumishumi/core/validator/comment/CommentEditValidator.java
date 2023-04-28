package id.thesis.shumishumi.core.validator.comment;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.comment.EditCommentRequest;

public class CommentEditValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof EditCommentRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        EditCommentRequest request = (EditCommentRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getCommentId(), "commentId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isNotEmpty(request.getContent(), "content", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
