package id.thesis.shumishumi.common.model.form.user.forgotpassword;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ForgotPasswordForm extends BaseForm {
    private static final long serialVersionUID = 6883322133804976424L;

    private String email;
}
