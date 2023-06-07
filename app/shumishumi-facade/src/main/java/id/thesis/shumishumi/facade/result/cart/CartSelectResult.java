package id.thesis.shumishumi.facade.result.cart;

import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartSelectResult extends BaseResult {
    private static final long serialVersionUID = -3319246640354417330L;

    private boolean selected;
}
