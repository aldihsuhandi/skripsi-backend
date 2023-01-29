package id.thesis.shumishumi.rest.result.item.wishlist;

import id.thesis.shumishumi.common.model.context.PagingContext;
import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.rest.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class QueryWishlistResult extends BaseResult {
    private static final long serialVersionUID = -9041997076558885835L;

    private PagingContext pagingContext;
    private List<ItemVO> wishlistItems;
}
