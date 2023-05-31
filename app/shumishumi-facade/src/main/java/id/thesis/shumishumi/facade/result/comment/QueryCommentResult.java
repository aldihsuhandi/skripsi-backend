package id.thesis.shumishumi.facade.result.comment;

import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.summary.CommentSummary;
import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class QueryCommentResult extends BaseResult {
    private static final long serialVersionUID = -220075206999215078L;

    private List<CommentSummary> comments;
    private PagingContext pagingContext;
}
