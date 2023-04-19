package id.thesis.shumishumi.facade.result.item.wishlist;

import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.summary.ItemSummary;
import id.thesis.shumishumi.facade.result.BaseResult;
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
