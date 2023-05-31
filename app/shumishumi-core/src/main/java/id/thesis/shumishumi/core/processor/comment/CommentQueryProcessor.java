package id.thesis.shumishumi.core.processor.comment;

import id.thesis.shumishumi.common.service.CommentService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.core.converter.SummaryConverter;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.summary.CommentSummary;
import id.thesis.shumishumi.facade.model.viewobject.CommentVO;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.comment.QueryCommentRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.comment.QueryCommentResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CommentQueryProcessor implements BaseProcessor {

    @Autowired
    private CommentService commentService;

    @Autowired
    private SessionService sessionService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        QueryCommentRequest request = (QueryCommentRequest) baseRequest;
        QueryCommentResult result = (QueryCommentResult) baseResult;

        PagingContext pagingContext = new PagingContext();
        pagingContext.setPageNumber(request.getPageNumber());
        pagingContext.setNumberOfItem(request.getNumberOfItem());

        List<CommentVO> comments = commentService.
                query(request.getReplyId(), request.getReplyTo(), pagingContext);
        pagingContext.calculateTotalPage();
        pagingContext.checkHasNext(pagingContext.getTotalItem(), comments.size());

        String userId;
        SessionVO sessionVO = sessionService.query(request.getSessionId());
        if (sessionVO != null) {
            userId = sessionVO.getUserId();
        } else {
            userId = "";
        }

        List<CommentSummary> summaries = new ArrayList<>();
        comments.forEach(comment -> {
            int userValue = commentService.queryVote(userId, comment.getCommentId());
            if (userValue == CommonConst.INT_NOT_FOUND) {
                userValue = 0;
            }

            comment.setCurrentUserVote(userValue);

            summaries.add(SummaryConverter.toSummary(comment));
        });

        result.setComments(summaries);
        result.setPagingContext(pagingContext);
    }
}
