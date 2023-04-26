package id.thesis.shumishumi.facade.request.user.forgotpassword;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ForgotPasswordQueryRequest extends BaseRequest {
    private static final long serialVersionUID = 2996453674556037103L;

    private String uuid;
}
