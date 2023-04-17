package id.thesis.shumishumi.rest.form.post;

import id.thesis.shumishumi.rest.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PostCreateForm extends BaseForm {
    private static final long serialVersionUID = -5669659866462200663L;

    private String title;
    private String content;
    private List<String> tags;
    private List<String> images;
}
