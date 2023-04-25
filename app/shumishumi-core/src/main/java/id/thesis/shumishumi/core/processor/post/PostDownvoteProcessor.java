package id.thesis.shumishumi.core.processor.post;

import id.thesis.shumishumi.common.service.PostService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.post.PostDownvoteRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.post.PostDownvoteResult;
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
            value = value == -1 ? 0 : -1;
            postService.updateVote(session.getUserId(), request.getPostId(), value);
        }

        result.setValue(value);
    }
}
