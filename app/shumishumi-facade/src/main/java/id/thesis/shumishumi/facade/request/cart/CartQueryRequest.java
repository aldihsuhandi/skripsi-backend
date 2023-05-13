package id.thesis.shumishumi.facade.request.cart;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartQueryRequest extends BaseRequest {
    private static final long serialVersionUID = -53626115769661185L;

    private int pageNumber;
    private int numberOfItem;
}
