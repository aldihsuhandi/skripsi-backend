package id.thesis.shumishumi.core.request.item.wishlist;

import id.thesis.shumishumi.common.model.context.ItemFilterContext;
import id.thesis.shumishumi.core.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QueryWishlistRequest extends BaseRequest {
    private static final long serialVersionUID = -6136967851659595896L;

    private int pageNumber = 1;
    private int numberOfItem = 10;
    private ItemFilterContext itemFilterContext;
}
