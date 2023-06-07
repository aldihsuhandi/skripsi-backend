package id.thesis.shumishumi.common.model.form.cart;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CartSelectForm extends BaseForm {
    private static final long serialVersionUID = 7019355242909082685L;

    private List<String> itemIds;
    private boolean selected = false;
}
