package id.thesis.shumishumi.core.processor.comment;

import id.thesis.shumishumi.common.service.CommentService;
import id.thesis.shumishumi.common.service.PostService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.CommentVO;
import id.thesis.shumishumi.facade.model.viewobject.PostVO;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.comment.CreateCommentRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.comment.CreateCommentResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentCreateProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        CreateCommentRequest request = (CreateCommentRequest) baseRequest;
        CreateCommentResult result = (CreateCommentResult) baseResult;

        SessionVO sessionVO = sessionService.query(request.getSessionId());
        UserVO user = userService.queryById(sessionVO.getUserId(), true);
        AssertUtil.isNotNull(user, "user not exist", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(user.isActive(), "user not active", ShumishumiErrorCodeEnum.USER_NOT_ACTIVE);
        AssertUtil.isExpected(!user.isDeleted(), "user not exist", ShumishumiErrorCodeEnum.USER_NOT_FOUND);

        if (StringUtils.equals(request.getReplyTo(), CommonConst.COMMENT_REPLY_POST)) {
            validatePost(request.getReplyId());
        } else if (StringUtils.equals(request.getReplyTo(), CommonConst.COMMENT_REPLY_COMMENT)) {
            validateComment(request.getReplyId());
        }

        commentService.create(user.getUserId(), request.getContent(),
                request.getReplyId(), request.getReplyTo());
    }

    private void validatePost(String postId) {
        PostVO post = postService.queryById(postId);
        AssertUtil.isNotNull(post, "post is not found", ShumishumiErrorCodeEnum.POST_NOT_FOUND);
        AssertUtil.isExpected(!post.isDeleted(), "post is not found", ShumishumiErrorCodeEnum.POST_NOT_FOUND);
    }

    private void validateComment(String commentId) {
        CommentVO comment = commentService.queryById(commentId);
        AssertUtil.isNotNull(comment, "comment is not found", ShumishumiErrorCodeEnum.COMMENT_NOT_FOUND);
        AssertUtil.isExpected(!comment.isDeleted(), "comment is not found", ShumishumiErrorCodeEnum.COMMENT_NOT_FOUND);
    }
}
