package id.thesis.shumishumi.core.processor.cart;

import id.thesis.shumishumi.common.service.CartService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.viewobject.CartVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.cart.CartQueryRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.cart.CartQueryResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CartQueryProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private CartService cartService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        CartQueryRequest request = (CartQueryRequest) baseRequest;
        CartQueryResult result = (CartQueryResult) baseResult;

        String userId = sessionService.query(request.getSessionId()).getUserId();
        PagingContext pagingContext = new PagingContext();
        pagingContext.setPageNumber(request.getPageNumber());
        pagingContext.setNumberOfItem(request.getNumberOfItem());

        List<CartVO> carts = cartService.queryCartList(userId, pagingContext);
        pagingContext.calculateTotalPage();
        pagingContext.checkHasNext(pagingContext.getTotalItem(), pagingContext.getNumberOfItem());
    }
}
