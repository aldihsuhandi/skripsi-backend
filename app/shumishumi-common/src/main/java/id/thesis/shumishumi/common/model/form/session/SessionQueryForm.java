package id.thesis.shumishumi.common.model.form.session;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SessionQueryForm extends BaseForm {
    private static final long serialVersionUID = 3940958652446004788L;

    private String sessionId;
}
