package id.thesis.shumishumi.common.model.form.user.email;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailEncryptForm extends BaseForm {
    private static final long serialVersionUID = 7615045528296125055L;

    private String email;
}
