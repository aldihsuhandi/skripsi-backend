package id.thesis.shumishumi.common.model.form.post;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PostEditForm extends BaseForm {
    private static final long serialVersionUID = 3206136961453744624L;

    private String postId;
    private String title;
    private String content;
    private List<String> tags;
    private List<String> images;
}
