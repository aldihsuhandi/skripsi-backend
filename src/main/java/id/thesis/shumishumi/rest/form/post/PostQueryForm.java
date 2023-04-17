package id.thesis.shumishumi.rest.form.post;

import id.thesis.shumishumi.rest.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostQueryForm extends BaseForm {
    private static final long serialVersionUID = -9075346761659555863L;

    private String postId;
}
