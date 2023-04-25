package id.thesis.shumishumi.facade.request.user.forgotpassword;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ForgotPasswordSendRequest extends BaseRequest {
    private static final long serialVersionUID = 7607334158015863794L;

    private String email;
}
