package id.thesis.shumishumi.core.processor.cart;

import id.thesis.shumishumi.common.service.CartService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.cart.CartSelectRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.cart.CartSelectResult;
import org.springframework.beans.factory.annotation.Autowired;

public class CartSelectProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private CartService cartService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        CartSelectRequest request = (CartSelectRequest) baseRequest;
        CartSelectResult result = (CartSelectResult) baseResult;

        String userId = sessionService.query(request.getSessionId()).getUserId();
        boolean selected = request.isSelected();
        cartService.selectCart(request.getItemIds(), userId, selected);

        result.setSelected(selected);
    }
}
