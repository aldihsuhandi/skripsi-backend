package id.thesis.shumishumi.facade.request.review;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReviewCreateRequest extends BaseRequest {
    private static final long serialVersionUID = -4536226523919217267L;

    private String reviewId;
    private int review;
    private List<String> images;
    private String description;
    private String interestLevel;
}
