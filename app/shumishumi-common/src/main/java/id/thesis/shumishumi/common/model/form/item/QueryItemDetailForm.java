package id.thesis.shumishumi.common.model.form.item;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QueryItemDetailForm extends BaseForm {
    private static final long serialVersionUID = -8798118376470205567L;

    private String itemId;
}
