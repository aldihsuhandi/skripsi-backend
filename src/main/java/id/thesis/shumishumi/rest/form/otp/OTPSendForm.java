package id.thesis.shumishumi.rest.form.otp;

import id.thesis.shumishumi.rest.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OTPSendForm extends BaseForm {
    private static final long serialVersionUID = -3008951167980072256L;

    private String email;
    private String otpType;
}
