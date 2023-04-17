package id.thesis.shumishumi.common.process.processor.post;

import id.thesis.shumishumi.common.process.processor.BaseProcessor;
import id.thesis.shumishumi.common.util.constant.CommonConst;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.post.PostUpvoteRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.result.post.PostUpvoteResult;
import id.thesis.shumishumi.core.service.PostService;
import id.thesis.shumishumi.core.service.SessionService;
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
