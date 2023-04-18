package id.thesis.shumishumi.common.model.form.otp;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OTPValidateForm extends BaseForm {
    private static final long serialVersionUID = -4150522427102471360L;

    private String email;
    private String otp;
    private String otpType;
}
