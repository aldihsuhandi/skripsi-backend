package id.thesis.shumishumi.rest.form.session;

import id.thesis.shumishumi.rest.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SessionLogoutForm extends BaseForm {
    private static final long serialVersionUID = 3297219264375660982L;

    private String sessionId;
}
