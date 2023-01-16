package id.thesis.shumishumi.rest.request.otp;

import id.thesis.shumishumi.rest.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OTPValidateRequest extends BaseRequest {
    private static final long serialVersionUID = 2407625915154354891L;

    private String email;
    private String otp;
    private String otpType;
}