package id.thesis.shumishumi.rest.form.post;

import id.thesis.shumishumi.rest.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class PostQueryListForm extends BaseForm {
    private static final long serialVersionUID = 4301249608535321964L;

    private String title;
    private List<String> tags = new ArrayList<>();
    private int pageNumber = 1;
    private int numberOfItem = 10;
}
