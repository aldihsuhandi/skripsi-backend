package id.thesis.shumishumi.facade.request.review;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewQueryRequest extends BaseRequest {
    private static final long serialVersionUID = 4781026314932675444L;

    private String type;
    private boolean needReview = false;
    private int numberOfItem = 10;
    private int pageNumber = 1;
}
