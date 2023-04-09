package id.thesis.shumishumi.rest.form.item;

import id.thesis.shumishumi.rest.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemApprovalForm extends BaseForm {
    private static final long serialVersionUID = 2939828511045875811L;

    private String itemId;
}
