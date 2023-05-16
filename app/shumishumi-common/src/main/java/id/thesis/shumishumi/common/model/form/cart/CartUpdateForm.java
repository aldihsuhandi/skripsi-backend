package id.thesis.shumishumi.common.model.form.cart;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartUpdateForm extends BaseForm {
    private static final long serialVersionUID = -7240397013138276533L;

    private String itemId;
    private Integer quantity;
}
