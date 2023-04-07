package id.thesis.shumishumi.rest.controller;

import id.thesis.shumishumi.core.facade.ItemWishlistFacade;
import id.thesis.shumishumi.core.process.callback.ControllerCallback;
import id.thesis.shumishumi.core.process.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.rest.request.HtmlRequest;
import id.thesis.shumishumi.rest.request.item.wishlist.AddWishlistRequest;
import id.thesis.shumishumi.rest.request.item.wishlist.QueryWishlistRequest;
import id.thesis.shumishumi.rest.request.item.wishlist.RemoveWishlistRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import id.thesis.shumishumi.rest.result.item.wishlist.AddWishlistResult;
import id.thesis.shumishumi.rest.result.item.wishlist.QueryWishlistResult;
import id.thesis.shumishumi.rest.result.item.wishlist.RemoveWishlistResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item/wishlist")
public class ItemWishlistController extends BaseController {
    @Autowired
    private ItemWishlistFacade itemWishlistFacade;

    @PostMapping("/add")
    public AddWishlistResult addWishlist(@RequestBody HtmlRequest<AddWishlistRequest> request) {
        return (AddWishlistResult) ControllerCallbackSupport.process(request.getHead(), request.getBody(), new ControllerCallback() {
            @Override
            public void authCheck() {
                authenticate(request.getHead());
            }

            @Override
            public BaseResult initResult() {
                return new AddWishlistResult();
            }

            @Override
            public BaseResult doProcess() {
                return itemWishlistFacade.add(request.getBody());
            }
        });
    }

    @PostMapping("/remove")
    public RemoveWishlistResult removeWishlist(@RequestBody HtmlRequest<RemoveWishlistRequest> request) {
        return (RemoveWishlistResult) ControllerCallbackSupport.process(request.getHead(), request.getBody(), new ControllerCallback() {
            @Override
            public void authCheck() {
                authenticate(request.getHead());
            }

            @Override
            public BaseResult initResult() {
                return new RemoveWishlistResult();
            }

            @Override
            public BaseResult doProcess() {
                return itemWishlistFacade.remove(request.getBody());
            }
        });
    }

    @PostMapping("/query")
    public QueryWishlistResult queryWishlist(@RequestBody HtmlRequest<QueryWishlistRequest> request) {
        return (QueryWishlistResult) ControllerCallbackSupport.process(request.getHead(), request.getBody(), new ControllerCallback() {
            @Override
            public void authCheck() {
                authenticate(request.getHead());
            }

            @Override
            public BaseResult initResult() {
                return new QueryWishlistResult();
            }

            @Override
            public BaseResult doProcess() {
                return itemWishlistFacade.query(request.getBody());
            }
        });
    }
}
