package id.thesis.shumishumi.common.model.form.post;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostDownvoteForm extends BaseForm {
    private static final long serialVersionUID = 1103260320630952640L;

    private String postId;
}
