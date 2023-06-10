package id.thesis.shumishumi.facade.request.review;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewDetailRequest extends BaseRequest {
    private static final long serialVersionUID = -3742373538278588524L;

    private String reviewId;
}
