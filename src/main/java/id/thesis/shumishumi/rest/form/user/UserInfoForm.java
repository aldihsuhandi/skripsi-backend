package id.thesis.shumishumi.rest.form.user;

import id.thesis.shumishumi.rest.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfoForm extends BaseForm {
    private static final long serialVersionUID = -2085174602500787866L;

    private String key;
    private String identifier;
}
