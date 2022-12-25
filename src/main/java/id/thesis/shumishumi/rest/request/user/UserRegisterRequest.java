package id.thesis.shumishumi.rest.request.user;

import id.thesis.shumishumi.rest.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRegisterRequest extends BaseRequest {
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String profilePicture;
}
