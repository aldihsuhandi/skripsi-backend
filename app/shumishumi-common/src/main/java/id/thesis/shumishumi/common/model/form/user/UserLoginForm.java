package id.thesis.shumishumi.common.model.form.user;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginForm extends BaseForm {
    private static final long serialVersionUID = 4682247649946296915L;

    private String email;
    private String password;
    private boolean isRemembered = false;
}
