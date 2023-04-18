package id.thesis.shumishumi.common.model.form.post;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostDeleteForm extends BaseForm {
    private static final long serialVersionUID = -2792849961966333487L;

    private String postId;
}
