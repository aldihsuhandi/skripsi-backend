package id.thesis.shumishumi.common.model.form.comment;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDownvoteForm extends BaseForm {
    private static final long serialVersionUID = 5508948713227980057L;

    private String commentId;
}
