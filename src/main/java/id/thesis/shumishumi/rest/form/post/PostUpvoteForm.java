package id.thesis.shumishumi.rest.form.post;

import id.thesis.shumishumi.rest.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostUpvoteForm extends BaseForm {
    private static final long serialVersionUID = 5810990936626752412L;

    private String postId;
}
