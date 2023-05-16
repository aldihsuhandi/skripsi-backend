package id.thesis.shumishumi.common.model.form.cart;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartQueryForm extends BaseForm {
    private static final long serialVersionUID = -7291973046920432856L;

    private int pageNumber = 1;
    private int numberOfItem = 10;
}
