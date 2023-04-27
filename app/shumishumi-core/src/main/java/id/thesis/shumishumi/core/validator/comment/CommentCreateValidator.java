package id.thesis.shumishumi.core.validator.comment;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.comment.CreateCommentRequest;
import org.apache.commons.lang3.StringUtils;

public class CommentCreateValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof CreateCommentRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        CreateCommentRequest request = (CreateCommentRequest) baseRequest;

        ParamChecker.isNotEmpty(request.getContent(), "content", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ParamChecker.isNotEmpty(request.getReplyId(), "replyId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        String replyTo = request.getReplyTo();
        ParamChecker.isNotEmpty(replyTo, "replyTo", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(StringUtils.equals(replyTo, CommonConst.COMMENT_REPLY_COMMENT)
                || StringUtils.equals(replyTo, CommonConst.COMMENT_REPLY_POST), "replyTo", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
