package id.thesis.shumishumi.web.controller;

import id.thesis.shumishumi.common.model.form.cart.CartAddForm;
import id.thesis.shumishumi.common.model.form.cart.CartCalculateForm;
import id.thesis.shumishumi.common.model.form.cart.CartQueryForm;
import id.thesis.shumishumi.common.model.form.cart.CartSelectForm;
import id.thesis.shumishumi.common.model.form.cart.CartUpdateForm;
import id.thesis.shumishumi.core.callback.ControllerCallback;
import id.thesis.shumishumi.core.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.facade.api.CartFacade;
import id.thesis.shumishumi.facade.request.cart.CartAddRequest;
import id.thesis.shumishumi.facade.request.cart.CartCalculateRequest;
import id.thesis.shumishumi.facade.request.cart.CartQueryRequest;
import id.thesis.shumishumi.facade.request.cart.CartSelectRequest;
import id.thesis.shumishumi.facade.request.cart.CartUpdateRequest;
import id.thesis.shumishumi.facade.result.cart.CartAddResult;
import id.thesis.shumishumi.facade.result.cart.CartCalculateResult;
import id.thesis.shumishumi.facade.result.cart.CartQueryResult;
import id.thesis.shumishumi.facade.result.cart.CartSelectResult;
import id.thesis.shumishumi.facade.result.cart.CartUpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController extends BaseController {
    @Autowired
    private CartFacade cartFacade;

    @PostMapping("/add")
    public ResponseEntity<CartAddResult> addToCart(@RequestHeader HttpHeaders headers, @RequestBody CartAddForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<CartAddResult, CartAddRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public CartAddRequest composeRequest() {
                CartAddRequest request = new CartAddRequest();
                request.setItemId(form.getItemId());
                request.setQuantity(form.getQuantity());

                return request;
            }

            @Override
            public CartAddResult doProcess(CartAddRequest request) {
                return cartFacade.add(request);
            }
        });
    }

    @PostMapping("/update")
    public ResponseEntity<CartUpdateResult> updateCart(@RequestHeader HttpHeaders headers, @RequestBody CartUpdateForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<CartUpdateResult, CartUpdateRequest>() {

            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public CartUpdateRequest composeRequest() {
                CartUpdateRequest request = new CartUpdateRequest();
                request.setItemId(form.getItemId());
                request.setQuantity(form.getQuantity());

                return request;
            }

            @Override
            public CartUpdateResult doProcess(CartUpdateRequest request) {
                return cartFacade.update(request);
            }
        });
    }

    @PostMapping("/query")
    public ResponseEntity<CartQueryResult> queryCart(@RequestHeader HttpHeaders headers, @RequestBody CartQueryForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<CartQueryResult, CartQueryRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public CartQueryRequest composeRequest() {
                CartQueryRequest request = new CartQueryRequest();
                request.setPageNumber(form.getPageNumber());
                request.setNumberOfItem(form.getNumberOfItem());

                return request;
            }

            @Override
            public CartQueryResult doProcess(CartQueryRequest request) {
                return cartFacade.query(request);
            }
        });
    }

    @PostMapping("/price")
    public ResponseEntity<CartCalculateResult> calculate(@RequestHeader HttpHeaders headers, @RequestBody CartCalculateForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<CartCalculateResult, CartCalculateRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public CartCalculateRequest composeRequest() {
                return new CartCalculateRequest();
            }

            @Override
            public CartCalculateResult doProcess(CartCalculateRequest request) {
                return cartFacade.calculatePrice(request);
            }
        });
    }

    @PostMapping("/select")
    public ResponseEntity<CartSelectResult> select(@RequestHeader HttpHeaders headers, @RequestBody CartSelectForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<CartSelectResult, CartSelectRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public CartSelectRequest composeRequest() {
                CartSelectRequest request = new CartSelectRequest();
                request.setSelected(form.isSelected());
                request.setItemIds(form.getItemIds());

                return request;
            }

            @Override
            public CartSelectResult doProcess(CartSelectRequest request) {
                return cartFacade.select(request);
            }
        });
    }
}
