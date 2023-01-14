package id.thesis.shumishumi.rest.request.otp;

import id.thesis.shumishumi.rest.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OTPSendRequest extends BaseRequest {
    private static final long serialVersionUID = 5949429099947405627L;

    private String email;

    private String otpType;
}
