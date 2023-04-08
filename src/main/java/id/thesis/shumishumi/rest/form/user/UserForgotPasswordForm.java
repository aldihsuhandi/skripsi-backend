package id.thesis.shumishumi.rest.form.user;

import id.thesis.shumishumi.rest.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserForgotPasswordForm extends BaseForm {
    private static final long serialVersionUID = 835379121630690776L;

    private String otp;
    private String email;
    private String password;
}
