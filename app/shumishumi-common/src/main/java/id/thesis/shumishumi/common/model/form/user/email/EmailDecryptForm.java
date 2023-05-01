package id.thesis.shumishumi.common.model.form.user.email;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailDecryptForm extends BaseForm {
    private static final long serialVersionUID = 9035660629482612413L;

    private String uuid;
}
