package id.thesis.shumishumi.common.model.form.review;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewQueryForm extends BaseForm {
    private static final long serialVersionUID = 3569197134562818760L;

    private String type;
    private String merchantName;
    private boolean needReview = false;
    private int numberOfItem = 10;
    private int pageNumber = 1;
}
