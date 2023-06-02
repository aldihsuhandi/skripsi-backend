package id.thesis.shumishumi.common.model.form.user;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;

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
    private String gender;
    private Date dateOfBirth;
    private Map<String, String> location;
    private String password;
    private String confirmPassword;
    private Boolean isDeleted;
    private Boolean isActive;
    private boolean canWhatsapp = false;
    private boolean canTelegram = false;
}
