package id.thesis.shumishumi.common.model.form.review;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewDetailForm extends BaseForm {
    private static final long serialVersionUID = -2117900754677368014L;

    private String reviewId;
}
