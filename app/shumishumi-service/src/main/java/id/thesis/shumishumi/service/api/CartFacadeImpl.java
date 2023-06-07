package id.thesis.shumishumi.service.api;

import id.thesis.shumishumi.core.callback.ProcessCallback;
import id.thesis.shumishumi.core.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.facade.api.CartFacade;
import id.thesis.shumishumi.facade.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.facade.request.cart.CartAddRequest;
import id.thesis.shumishumi.facade.request.cart.CartCalculateRequest;
import id.thesis.shumishumi.facade.request.cart.CartQueryRequest;
import id.thesis.shumishumi.facade.request.cart.CartSelectRequest;
import id.thesis.shumishumi.facade.request.cart.CartUpdateRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.cart.CartAddResult;
import id.thesis.shumishumi.facade.result.cart.CartCalculateResult;
import id.thesis.shumishumi.facade.result.cart.CartQueryResult;
import id.thesis.shumishumi.facade.result.cart.CartSelectResult;
import id.thesis.shumishumi.facade.result.cart.CartUpdateResult;
import org.springframework.stereotype.Service;

@Service
public class CartFacadeImpl extends ProcessFacade implements CartFacade {

    @Override
    public CartQueryResult query(CartQueryRequest request) {
        return (CartQueryResult) ProcessCallbackSupport.process(ProcessTypeEnum.CART_QUERY, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new CartQueryResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public CartAddResult add(CartAddRequest request) {
        return (CartAddResult) ProcessCallbackSupport.process(ProcessTypeEnum.CART_ADD, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new CartAddResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public CartUpdateResult update(CartUpdateRequest request) {
        return (CartUpdateResult) ProcessCallbackSupport.process(ProcessTypeEnum.CART_UPDATE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new CartUpdateResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public CartCalculateResult calculatePrice(CartCalculateRequest request) {
        return (CartCalculateResult) ProcessCallbackSupport.process(ProcessTypeEnum.CART_CALCULATE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new CartCalculateResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public CartSelectResult select(CartSelectRequest request) {
        return (CartSelectResult) ProcessCallbackSupport.process(ProcessTypeEnum.CART_SELECT, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new CartSelectResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }
}
