package id.thesis.shumishumi.core.result.item.wishlist;

import id.thesis.shumishumi.common.model.context.PagingContext;
import id.thesis.shumishumi.common.model.summary.ItemSummary;
import id.thesis.shumishumi.core.result.BaseResult;
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
    private List<ItemSummary> wishlistItems;
}
