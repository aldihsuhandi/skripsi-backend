package id.thesis.shumishumi.common.model.form.user;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserRegisterForm extends BaseForm {
    private static final long serialVersionUID = -802848883345557502L;

    private String username;
    private String email;
    private String phoneNumber;
    private String gender;
    private Date dateOfBirth;
    private MultipartFile profilePicture;
    private String password;
    private String confirmPassword;
}
