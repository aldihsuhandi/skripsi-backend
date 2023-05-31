package id.thesis.shumishumi.common.model.form.comment;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentQueryForm extends BaseForm {
    private static final long serialVersionUID = -1107994170646453969L;

    private String replyTo;
    private String replyId;
    private int pageNumber = 1;
    private int numberOfItem = 10;
}