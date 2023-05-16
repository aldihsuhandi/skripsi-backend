package id.thesis.shumishumi.core.processor.cart;

import id.thesis.shumishumi.common.service.CartService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.cart.CartCalculateRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.cart.CartCalculateResult;
import org.springframework.beans.factory.annotation.Autowired;

public class CartCalculateProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private CartService cartService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        CartCalculateRequest request = (CartCalculateRequest) baseRequest;
        CartCalculateResult result = (CartCalculateResult) baseResult;

        String userId = sessionService.query(request.getSessionId()).getUserId();

        Long price = cartService.calculatePrice(userId);
        result.setPrice(price);
    }
}
