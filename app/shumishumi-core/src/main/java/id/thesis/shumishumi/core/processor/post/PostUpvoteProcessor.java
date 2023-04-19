package id.thesis.shumishumi.core.processor.post;

import id.thesis.shumishumi.common.service.PostService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.post.PostUpvoteRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.post.PostUpvoteResult;
import org.springframework.beans.factory.annotation.Autowired;

public class PostUpvoteProcessor implements BaseProcessor {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private PostService postService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        PostUpvoteRequest request = (PostUpvoteRequest) baseRequest;
        PostUpvoteResult result = (PostUpvoteResult) baseResult;

        String userId = sessionService.query(request.getSessionId()).getUserId();

        int vote = postService.queryPostVote(userId, request.getPostId());
        if (vote == CommonConst.INT_NOT_FOUND) {
            vote = 1;
            postService.insertVote(userId, request.getPostId(), vote);
        } else {
            vote = vote == 1 ? 0 : 1;
            postService.updateVote(userId, request.getPostId(), vote);
        }

        result.setValue(vote);
    }
}
