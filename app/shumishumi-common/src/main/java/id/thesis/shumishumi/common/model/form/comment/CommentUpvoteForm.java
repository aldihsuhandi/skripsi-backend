package id.thesis.shumishumi.common.model.form.comment;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentUpvoteForm extends BaseForm {
    private static final long serialVersionUID = -7531531554303758126L;

    private String commentId;
}
