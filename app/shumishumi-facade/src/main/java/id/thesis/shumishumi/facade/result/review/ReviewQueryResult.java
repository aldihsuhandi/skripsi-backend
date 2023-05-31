package id.thesis.shumishumi.facade.result.review;

import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.summary.ReviewSummary;
import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReviewQueryResult extends BaseResult {
    private static final long serialVersionUID = 7362795308781521171L;

    private List<ReviewSummary> reviews;
    private PagingContext pagingContext;
}
