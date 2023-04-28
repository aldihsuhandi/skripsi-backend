package id.thesis.shumishumi.core.processor.comment;

import id.thesis.shumishumi.common.service.CommentService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.comment.DownvoteCommentRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.comment.DownvoteCommentResult;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentDownvoteProcessor implements BaseProcessor {

    @Autowired
    private CommentService commentService;

    @Autowired
    private SessionService sessionService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        DownvoteCommentRequest request = (DownvoteCommentRequest) baseRequest;
        DownvoteCommentResult result = (DownvoteCommentResult) baseResult;

        String userId = sessionService.query(request.getSessionId()).getUserId();
        int vote = commentService.queryVote(userId, request.getCommentId());
        if (vote == CommonConst.INT_NOT_FOUND) {
            vote = -1;
            commentService.insertVote(userId, request.getCommentId(), vote);
        } else {
            vote = vote == -1 ? 0 : -1;
            commentService.updateVote(userId, request.getCommentId(), vote);
        }

        result.setValue(vote);
    }
}
