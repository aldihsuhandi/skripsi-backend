package id.thesis.shumishumi.common.model.request.otp;

import id.thesis.shumishumi.common.model.request.BaseInnerRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OTPSendInnerRequest extends BaseInnerRequest {
    private static final long serialVersionUID = 5544374540739907502L;

    private String email;
    private String otp;
    private String otpType;
}
