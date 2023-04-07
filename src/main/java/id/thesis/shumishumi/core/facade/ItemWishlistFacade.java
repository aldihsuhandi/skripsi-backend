package id.thesis.shumishumi.core.facade;

import id.thesis.shumishumi.rest.request.item.wishlist.AddWishlistRequest;
import id.thesis.shumishumi.rest.request.item.wishlist.QueryWishlistRequest;
import id.thesis.shumishumi.rest.request.item.wishlist.RemoveWishlistRequest;
import id.thesis.shumishumi.rest.result.item.wishlist.AddWishlistResult;
import id.thesis.shumishumi.rest.result.item.wishlist.QueryWishlistResult;
import id.thesis.shumishumi.rest.result.item.wishlist.RemoveWishlistResult;

public interface ItemWishlistFacade {
    AddWishlistResult add(AddWishlistRequest request);

    RemoveWishlistResult remove(RemoveWishlistRequest request);

    QueryWishlistResult query(QueryWishlistRequest request);
}
