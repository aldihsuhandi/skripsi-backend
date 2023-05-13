package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.cart.CartAddRequest;
import id.thesis.shumishumi.facade.request.cart.CartCalculateRequest;
import id.thesis.shumishumi.facade.request.cart.CartQueryRequest;
import id.thesis.shumishumi.facade.request.cart.CartUpdateRequest;
import id.thesis.shumishumi.facade.result.cart.CartAddResult;
import id.thesis.shumishumi.facade.result.cart.CartCalculateResult;
import id.thesis.shumishumi.facade.result.cart.CartQueryResult;
import id.thesis.shumishumi.facade.result.cart.CartUpdateResult;

public interface CartFacade {
    CartQueryResult query(CartQueryRequest request);

    CartAddResult add(CartAddRequest request);

    CartUpdateResult update(CartUpdateRequest request);

    CartCalculateResult calculatePrice(CartCalculateRequest request);
}
