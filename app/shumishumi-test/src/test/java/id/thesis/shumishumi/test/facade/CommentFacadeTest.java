package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.facade.api.CommentFacade;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.request.comment.CreateCommentRequest;
import id.thesis.shumishumi.facade.request.comment.DeleteCommentRequest;
import id.thesis.shumishumi.facade.request.comment.DownvoteCommentRequest;
import id.thesis.shumishumi.facade.request.comment.EditCommentRequest;
import id.thesis.shumishumi.facade.request.comment.QueryCommentRequest;
import id.thesis.shumishumi.facade.request.comment.UpvoteCommentRequest;
import id.thesis.shumishumi.facade.result.comment.CreateCommentResult;
import id.thesis.shumishumi.facade.result.comment.DeleteCommentResult;
import id.thesis.shumishumi.facade.result.comment.DownvoteCommentResult;
import id.thesis.shumishumi.facade.result.comment.EditCommentResult;
import id.thesis.shumishumi.facade.result.comment.QueryCommentResult;
import id.thesis.shumishumi.facade.result.comment.UpvoteCommentResult;
import id.thesis.shumishumi.foundation.model.result.CommentDO;
import id.thesis.shumishumi.foundation.model.result.PostDO;
import id.thesis.shumishumi.foundation.model.result.PostVoteDO;
import id.thesis.shumishumi.foundation.model.result.primarykey.PostVoteDOPK;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentFacadeTest extends FacadeTestBase {
    @Autowired
    private CommentFacade commentFacade;

    @Test
    public void createCommentTest_SUCCESS() {
        CreateCommentRequest request = new CreateCommentRequest();
        request.setContent("content");
        request.setReplyTo(CommonConst.COMMENT_REPLY_POST);
        request.setReplyId("post_id");

        mockitoSession("userId");
        mockUserWithRole();
        Mockito.when(postDAO.queryById(Mockito.any())).thenReturn(mockPostDO("userId", "keyboard|switches", ""));

        CreateCommentResult result = commentFacade.create(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void updateCommentTest_SUCCESS() {
        EditCommentRequest request = new EditCommentRequest();
        request.setCommentId("commentId");
        request.setContent("updated comment");

        mockitoSession("userId");
        mockUserWithRole();
        Mockito.when(commentDAO.queryById(Mockito.any())).thenReturn(mockCommentDO("userId"));

        EditCommentResult result = commentFacade.edit(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void deleteCommentTest_SUCCESS() {
        DeleteCommentRequest request = new DeleteCommentRequest();
        request.setCommentId("commentId");

        mockitoSession("userId");
        mockUserWithRole();
        Mockito.when(commentDAO.queryById(Mockito.any())).thenReturn(mockCommentDO("userId"));

        DeleteCommentResult result = commentFacade.delete(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void queryCommentTest_SUCCESS() {
        QueryCommentRequest request = new QueryCommentRequest();
        request.setReplyId("post_id");
        request.setReplyTo(CommonConst.COMMENT_REPLY_POST);

        mockUserWithRole();
        Mockito.when(commentDAO.queryPostComment(Mockito.any(), Mockito.any()))
                .thenReturn(mockComments());

        QueryCommentResult result = commentFacade.query(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void upvoteCommentTest_SUCCESS() {
        UpvoteCommentRequest request = new UpvoteCommentRequest();
        request.setCommentId("comment_id");

        mockitoSession("userId");
        mockUserWithRole();

        UpvoteCommentResult result = commentFacade.upvote(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void downvoteCommentTest_SUCCESS() {
        DownvoteCommentRequest request = new DownvoteCommentRequest();
        request.setCommentId("comment_id");

        mockitoSession("userId");
        mockUserWithRole();

        DownvoteCommentResult result = commentFacade.downvote(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    private List<CommentDO> mockComments() {
        List<CommentDO> comments = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            comments.add(mockCommentDO("user" + i));
        }

        return comments;
    }

    private CommentDO mockCommentDO(String userId) {
        CommentDO commentDO = new CommentDO();
        commentDO.setCommentId("comment");
        commentDO.setUserId(userId);

        return commentDO;
    }

    private PostDO mockPostDO(String userId, String tags, String images) {
        PostDO postDO = new PostDO();
        postDO.setPostId(FunctionUtil.generateUUID());
        postDO.setUserId(userId);
        postDO.setTitle("title");
        postDO.setContent("content");
        postDO.setTags(tags);
        postDO.setImages(images);
        postDO.setGmtCreate(new Date());
        postDO.setGmtModified(new Date());

        return postDO;
    }


    private PostVoteDO mockPostVote(String userId, int value) {
        PostVoteDO voteDO = new PostVoteDO();
        voteDO.setPk(new PostVoteDOPK(userId, "postId"));
        voteDO.setValue(value);

        return voteDO;
    }
}
