package id.thesis.shumishumi.facade.request.user;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;

@Getter
@Setter
@ToString
public class UserRegisterRequest extends BaseRequest {
    private String username;
    private String email;
    private String phoneNumber;
    private Blob profilePicture;
    private String password;
}
