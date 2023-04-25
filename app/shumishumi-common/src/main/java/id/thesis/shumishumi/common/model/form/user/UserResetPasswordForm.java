package id.thesis.shumishumi.common.model.form.user;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserResetPasswordForm extends BaseForm {
    private static final long serialVersionUID = 835379121630690776L;

    private String otp;
    private String email;
    private String password;
    private String confirmPassword;
}
