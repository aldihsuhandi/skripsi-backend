package id.thesis.shumishumi.facade.request.cart;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartAddRequest extends BaseRequest {
    private static final long serialVersionUID = 3238376557680336675L;

    private String itemId;
    private Integer quantity;
}
