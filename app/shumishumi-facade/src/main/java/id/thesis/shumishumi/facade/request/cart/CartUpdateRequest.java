package id.thesis.shumishumi.facade.request.cart;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartUpdateRequest extends BaseRequest {
    private static final long serialVersionUID = -5751583456221560675L;

    private String itemId;
    private Integer quantity;
}
