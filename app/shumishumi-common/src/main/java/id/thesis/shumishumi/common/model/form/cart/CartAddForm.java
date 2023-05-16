package id.thesis.shumishumi.common.model.form.cart;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartAddForm extends BaseForm {
    private static final long serialVersionUID = 8775796079213504867L;

    private String itemId;
    private Integer quantity;
}
