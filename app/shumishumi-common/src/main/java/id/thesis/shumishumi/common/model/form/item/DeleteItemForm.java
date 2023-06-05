package id.thesis.shumishumi.common.model.form.item;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeleteItemForm extends BaseForm {
    private static final long serialVersionUID = 1236733674729353737L;

    private String itemId;
}
