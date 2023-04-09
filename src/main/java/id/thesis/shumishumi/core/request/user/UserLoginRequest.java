package id.thesis.shumishumi.core.request.user;

import id.thesis.shumishumi.core.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginRequest extends BaseRequest {
    private String email;
    private String password;
    private boolean isRemembered;
}
