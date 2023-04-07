package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.viewobject.ItemWishlistVO;

import java.util.List;

public interface ItemWishlistService {

    void addWishlist(String userId, String itemId);

    void removeWishlist(String userId, String itemId);

    List<ItemWishlistVO> queryUserWishlist(String userId);

    int countItemWishlist(String itemId);

}
