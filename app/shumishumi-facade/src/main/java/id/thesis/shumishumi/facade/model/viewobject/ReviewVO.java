package id.thesis.shumishumi.facade.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReviewVO extends BaseVO {
    private static final long serialVersionUID = -2641368104380023687L;

    private String reviewId;
    private String itemId;
    private String userId;
    private String merchantId;
    private String description;
    private List<String> reviewImages;
    private InterestLevelVO interestLevel;
    private int star;
    private boolean needReview;
}
