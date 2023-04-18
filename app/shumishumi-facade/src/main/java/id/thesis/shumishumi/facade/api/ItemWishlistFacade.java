package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.item.wishlist.AddWishlistRequest;
import id.thesis.shumishumi.facade.request.item.wishlist.QueryWishlistRequest;
import id.thesis.shumishumi.facade.request.item.wishlist.RemoveWishlistRequest;
import id.thesis.shumishumi.facade.result.item.wishlist.AddWishlistResult;
import id.thesis.shumishumi.facade.result.item.wishlist.QueryWishlistResult;
import id.thesis.shumishumi.facade.result.item.wishlist.RemoveWishlistResult;

public interface ItemWishlistFacade {
    AddWishlistResult add(AddWishlistRequest request);

    RemoveWishlistResult remove(RemoveWishlistRequest request);

    QueryWishlistResult query(QueryWishlistRequest request);
}
