package id.thesis.shumishumi.common.model.form.comment;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDeleteForm extends BaseForm {
    private static final long serialVersionUID = 1361528012700612370L;

    private String commentId;
}
