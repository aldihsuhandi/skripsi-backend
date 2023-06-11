package id.thesis.shumishumi.facade.result.review;

import id.thesis.shumishumi.facade.model.summary.ItemSummary;
import id.thesis.shumishumi.facade.model.summary.ReviewSummary;
import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewDetailResult extends BaseResult {
    private static final long serialVersionUID = 289827295210724112L;

    private ReviewSummary review;
    private ItemSummary item;
}
