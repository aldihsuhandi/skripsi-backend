package id.thesis.shumishumi.core.processor.comment;

import id.thesis.shumishumi.common.service.CommentService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.CommentVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.comment.DeleteCommentRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentDeleteProcessor implements BaseProcessor {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private CommentService commentService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        DeleteCommentRequest request = (DeleteCommentRequest) baseRequest;

        String userId = sessionService.query(request.getSessionId()).getUserId();
        UserVO user = userService.queryById(userId, true);

        AssertUtil.isNotNull(user, "user not exist", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(user.isActive(), "user not active", ShumishumiErrorCodeEnum.USER_NOT_ACTIVE);
        AssertUtil.isExpected(!user.isDeleted(), "user not exist", ShumishumiErrorCodeEnum.USER_NOT_FOUND);

        CommentVO comment = commentService.queryById(request.getCommentId());
        AssertUtil.isNotNull(comment, "comment is not found", ShumishumiErrorCodeEnum.COMMENT_NOT_FOUND);
        AssertUtil.isExpected(!comment.isDeleted(), "comment is not found", ShumishumiErrorCodeEnum.COMMENT_NOT_FOUND);
        AssertUtil.isExpected(StringUtils.equals(userId, comment.getCommenter().getUserId()), "invalid user for this operation",
                ShumishumiErrorCodeEnum.USER_INVALID);

        commentService.delete(comment.getCommentId());
    }
}
