package id.thesis.shumishumi.facade.request.cart;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CartSelectRequest extends BaseRequest {
    private static final long serialVersionUID = 1777922613342174068L;

    private List<String> itemIds;
    private boolean selected;
}
