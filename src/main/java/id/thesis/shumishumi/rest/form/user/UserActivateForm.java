package id.thesis.shumishumi.rest.form.user;

import id.thesis.shumishumi.rest.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserActivateForm extends BaseForm {
    private static final long serialVersionUID = -5428929342509509725L;

    private String email;
    private String otp;
}
