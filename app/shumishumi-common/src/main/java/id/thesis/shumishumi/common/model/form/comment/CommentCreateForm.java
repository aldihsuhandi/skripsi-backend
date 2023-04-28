package id.thesis.shumishumi.common.model.form.comment;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentCreateForm extends BaseForm {
    private static final long serialVersionUID = -4928413175452915293L;

    private String content;
    private String replyTo;
    private String replyId;
}
