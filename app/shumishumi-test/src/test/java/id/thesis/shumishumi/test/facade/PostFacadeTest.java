package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.facade.api.PostFacade;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.post.PostCreateRequest;
import id.thesis.shumishumi.facade.request.post.PostDeleteRequest;
import id.thesis.shumishumi.facade.request.post.PostDownvoteRequest;
import id.thesis.shumishumi.facade.request.post.PostEditRequest;
import id.thesis.shumishumi.facade.request.post.PostQueryListRequest;
import id.thesis.shumishumi.facade.request.post.PostQueryRequest;
import id.thesis.shumishumi.facade.request.post.PostUpvoteRequest;
import id.thesis.shumishumi.facade.result.post.PostCreateResult;
import id.thesis.shumishumi.facade.result.post.PostDeleteResult;
import id.thesis.shumishumi.facade.result.post.PostDownvoteResult;
import id.thesis.shumishumi.facade.result.post.PostEditResult;
import id.thesis.shumishumi.facade.result.post.PostQueryListResult;
import id.thesis.shumishumi.facade.result.post.PostQueryResult;
import id.thesis.shumishumi.facade.result.post.PostUpvoteResult;
import id.thesis.shumishumi.foundation.model.result.PostDO;
import id.thesis.shumishumi.foundation.model.result.PostVoteDO;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Date;

public class PostFacadeTest extends FacadeTestBase {
    @Autowired
    private PostFacade postFacade;

    @BeforeEach
    public void before() {
        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO("userId"));
    }

    @Test
    public void postCreateTest_SUCCESS() {
        PostCreateRequest request = new PostCreateRequest();
        request.setTitle("title");
        request.setTags(Collections.singletonList("tags"));

        PostCreateResult result = postFacade.create(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void postEditTest_SUCCESS() {
        PostEditRequest request = new PostEditRequest();
        request.setPostId("postId");
        request.setTitle("new title");

        Mockito.when(postDAO.queryById(Mockito.any())).thenReturn(mockPostDO("userId", "keyboard|switches", ""));

        PostEditResult result = postFacade.edit(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void postEditTest_FAILED_invalidUser() {
        PostEditRequest request = new PostEditRequest();
        request.setPostId("postId");
        request.setTitle("new title");

        Mockito.when(postDAO.queryById(Mockito.any())).thenReturn(mockPostDO("heheId", "keyboard|switches", ""));

        PostEditResult result = postFacade.edit(request);
        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.USER_INVALID.getErrorCode());
    }

    @Test
    public void postDeleteTest_SUCCESS() {
        PostDeleteRequest request = new PostDeleteRequest();
        request.setPostId("postId");

        Mockito.when(postDAO.queryById(Mockito.any())).thenReturn(mockPostDO("userId", "keyboard|switches", ""));

        PostDeleteResult result = postFacade.delete(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void postUpvoteTest_SUCCESS_new() {
        PostUpvoteRequest request = new PostUpvoteRequest();
        request.setPostId("postId");

        PostUpvoteResult result = postFacade.upvote(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void postUpvoteTest_SUCCESS_existing() {
        PostUpvoteRequest request = new PostUpvoteRequest();
        request.setPostId("postId");

        Mockito.when(postVoteDAO.queryUserVote(Mockito.any(), Mockito.any())).
                thenReturn(mockPostVote("userId", 1));

        PostUpvoteResult result = postFacade.upvote(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void postDownvoteTest_SUCCESS_new() {
        PostDownvoteRequest request = new PostDownvoteRequest();
        request.setPostId("postId");

        PostDownvoteResult result = postFacade.downvote(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void postDownvoteTest_SUCCESS_existing() {
        PostDownvoteRequest request = new PostDownvoteRequest();
        request.setPostId("postId");

        Mockito.when(postVoteDAO.queryUserVote(Mockito.any(), Mockito.any())).
                thenReturn(mockPostVote("userId", 1));

        PostDownvoteResult result = postFacade.downvote(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void postDetailQueryTest_SUCCESS() {
        PostQueryRequest request = new PostQueryRequest();
        request.setPostId("postId");

        Mockito.when(postDAO.queryById(Mockito.any())).thenReturn(mockPostDO("userId", "keyboard|switches", ""));
        Mockito.when(postVoteDAO.queryVote(Mockito.any(), Mockito.eq(-1))).thenReturn(10);
        Mockito.when(postVoteDAO.queryVote(Mockito.any(), Mockito.eq(1))).thenReturn(200);
        mockUserWithRole();

        PostQueryResult result = postFacade.queryDetail(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void postQueryTest_SUCCESS() {
        PostQueryListRequest request = new PostQueryListRequest();

        Mockito.when(postDAO.query(Mockito.any())).thenReturn(
                Collections.singletonList(mockPostDO("userId", "keyboard|switches", "")));
        Mockito.when(postDAO.countList(Mockito.any())).thenReturn(230);
        Mockito.when(postVoteDAO.queryVote(Mockito.any(), Mockito.eq(-1))).thenReturn(10);
        Mockito.when(postVoteDAO.queryVote(Mockito.any(), Mockito.eq(1))).thenReturn(200);
        mockUserWithRole();

        PostQueryListResult result = postFacade.queryList(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
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
        voteDO.setPostId("id");
        voteDO.setUserId(userId);
        voteDO.setValue(value);

        return voteDO;
    }
}
