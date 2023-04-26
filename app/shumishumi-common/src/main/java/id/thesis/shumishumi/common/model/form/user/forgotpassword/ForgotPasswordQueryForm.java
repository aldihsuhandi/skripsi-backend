package id.thesis.shumishumi.common.model.form.user.forgotpassword;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ForgotPasswordQueryForm extends BaseForm {
    private static final long serialVersionUID = 3215340134995397952L;

    private String uuid;
}
