package id.thesis.shumishumi.facade.model.summary;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReviewSummary extends BaseSummary {
    private static final long serialVersionUID = -5249788364869313709L;

    private String reviewId;
    private String itemId;
    private int review;
    private List<String> images;
    private String interestLevel;
    private String description;
    private boolean needReview = false;
}
