package id.thesis.shumishumi.core.request.user;

import id.thesis.shumishumi.core.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserForgotPasswordRequest extends BaseRequest {
    private static final long serialVersionUID = 2273854508316261153L;

    private String email;
    private String password;
    private String otp;
}
