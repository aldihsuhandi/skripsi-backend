package id.thesis.shumishumi.facade.request.item.wishlist;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RemoveWishlistRequest extends BaseRequest {
    private static final long serialVersionUID = 4236280630623004172L;

    private String itemId;
}
