package id.thesis.shumishumi.rest.request.user;

import id.thesis.shumishumi.rest.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserActivateRequest extends BaseRequest {
    private static final long serialVersionUID = -849701650176579987L;

    private String email;
    private String otp;
}
