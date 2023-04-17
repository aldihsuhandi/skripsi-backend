package id.thesis.shumishumi.common.process.processor.post;

import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.common.process.processor.BaseProcessor;
import id.thesis.shumishumi.common.util.constant.CommonConst;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.post.PostDownvoteRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.result.post.PostDownvoteResult;
import id.thesis.shumishumi.core.service.PostService;
import id.thesis.shumishumi.core.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostDownvoteProcessor implements BaseProcessor {

    @Autowired
    private PostService postService;

    @Autowired
    private SessionService sessionService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        PostDownvoteRequest request = (PostDownvoteRequest) baseRequest;
        PostDownvoteResult result = (PostDownvoteResult) baseResult;

        SessionVO session = sessionService.query(request.getSessionId());

        int value = postService.queryPostVote(session.getUserId(), request.getPostId());
        if (value == CommonConst.INT_NOT_FOUND) {
            value = -1;
            postService.insertVote(session.getUserId(), request.getPostId(), value);
        } else {
            value = value == 0 ? -1 : 0;
            postService.updateVote(session.getUserId(), request.getPostId(), value);
        }

        result.setValue(value);
    }
}
