package id.thesis.shumishumi.facade.result.cart;

import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.summary.CartSummary;
import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CartQueryResult extends BaseResult {
    private static final long serialVersionUID = 3228040019683027047L;

    private PagingContext pagingContext;
    private List<CartSummary> carts;
    private Long price;
}
