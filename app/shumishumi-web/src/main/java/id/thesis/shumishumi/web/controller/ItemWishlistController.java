package id.thesis.shumishumi.web.controller;

import id.thesis.shumishumi.common.model.form.item.wishlist.AddWishlistForm;
import id.thesis.shumishumi.common.model.form.item.wishlist.QueryWishlistForm;
import id.thesis.shumishumi.common.model.form.item.wishlist.RemoveWishlistForm;
import id.thesis.shumishumi.core.callback.ControllerCallback;
import id.thesis.shumishumi.core.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.facade.api.ItemWishlistFacade;
import id.thesis.shumishumi.facade.request.item.wishlist.AddWishlistRequest;
import id.thesis.shumishumi.facade.request.item.wishlist.QueryWishlistRequest;
import id.thesis.shumishumi.facade.request.item.wishlist.RemoveWishlistRequest;
import id.thesis.shumishumi.facade.result.item.wishlist.AddWishlistResult;
import id.thesis.shumishumi.facade.result.item.wishlist.QueryWishlistResult;
import id.thesis.shumishumi.facade.result.item.wishlist.RemoveWishlistResult;
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
@RequestMapping("/item/wishlist")
public class ItemWishlistController extends BaseController {
    @Autowired
    private ItemWishlistFacade itemWishlistFacade;

    @PostMapping("/add")
    public ResponseEntity<AddWishlistResult> addWishlist(@RequestHeader HttpHeaders headers, @RequestBody AddWishlistForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<AddWishlistResult, AddWishlistRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public AddWishlistRequest composeRequest() {
                AddWishlistRequest request = new AddWishlistRequest();
                request.setItemId(form.getItemId());

                return request;
            }

            @Override
            public AddWishlistResult doProcess(AddWishlistRequest request) {
                return itemWishlistFacade.add(request);
            }
        });
    }

    @PostMapping("/remove")
    public ResponseEntity<RemoveWishlistResult> removeWishlist(@RequestHeader HttpHeaders headers, @RequestBody RemoveWishlistForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<RemoveWishlistResult, RemoveWishlistRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public RemoveWishlistRequest composeRequest() {
                RemoveWishlistRequest request = new RemoveWishlistRequest();
                request.setItemId(form.getItemId());

                return request;
            }

            @Override
            public RemoveWishlistResult doProcess(RemoveWishlistRequest request) {
                return itemWishlistFacade.remove(request);
            }
        });
    }

    @PostMapping("/query")
    public ResponseEntity<QueryWishlistResult> queryWishlist(@RequestHeader HttpHeaders headers, @RequestBody QueryWishlistForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<QueryWishlistResult, QueryWishlistRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public QueryWishlistRequest composeRequest() {
                QueryWishlistRequest request = new QueryWishlistRequest();
                request.setNumberOfItem(form.getNumberOfItem());
                request.setItemFilterContext(form.getItemFilterContext());
                request.setPageNumber(form.getPageNumber());

                return request;
            }

            @Override
            public QueryWishlistResult doProcess(QueryWishlistRequest request) {
                return itemWishlistFacade.query(request);
            }
        });
    }
}
