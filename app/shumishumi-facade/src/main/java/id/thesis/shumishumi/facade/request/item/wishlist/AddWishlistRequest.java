package id.thesis.shumishumi.facade.request.item.wishlist;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddWishlistRequest extends BaseRequest {
    private static final long serialVersionUID = -7405111098831685086L;

    private String itemId;
}
