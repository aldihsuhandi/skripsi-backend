package id.thesis.shumishumi.facade.result.cart;

import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartCalculateResult extends BaseResult {
    private static final long serialVersionUID = 4516885354321686545L;

    private Long price;
}
