package id.thesis.shumishumi.rest.form.user;

import id.thesis.shumishumi.rest.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class UserUpdateForm extends BaseForm {
    private static final long serialVersionUID = 7776364179134580095L;

    private String oldPassword;
    private String username;
    private String email;
    private String phoneNumber;
    private MultipartFile profilePicture;
    private String password;
    private String confirmPassword;
    private boolean isDeleted;
    private boolean isActive;
}
