package id.thesis.shumishumi.common.model.form.comment;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentEditForm extends BaseForm {
    private static final long serialVersionUID = 5078872880287983990L;

    private String commentId;
    private String content;
}
