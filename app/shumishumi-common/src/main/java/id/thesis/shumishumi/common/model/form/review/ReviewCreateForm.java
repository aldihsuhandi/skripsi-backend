package id.thesis.shumishumi.common.model.form.review;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReviewCreateForm extends BaseForm {
    private static final long serialVersionUID = -2396520058749174739L;

    private String reviewId;
    private int review;
    private List<String> images;
    private String description;
    private String interestLevel;
}
