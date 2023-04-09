package id.thesis.shumishumi.core.request.user;

import id.thesis.shumishumi.core.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class UserRegisterRequest extends BaseRequest {
    private String username;
    private String email;
    private String phoneNumber;
    private MultipartFile profilePicture;
    private String password;
    private String confirmPassword;
}
