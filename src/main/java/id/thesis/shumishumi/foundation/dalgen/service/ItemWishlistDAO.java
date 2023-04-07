package id.thesis.shumishumi.foundation.dalgen.service;

import id.thesis.shumishumi.foundation.dalgen.model.result.ItemWishlistDO;

import java.util.List;

public interface ItemWishlistDAO {
    void addWishlist(String userId, String itemId);

    void removeWishlist(String userId, String itemId);

    List<ItemWishlistDO> queryUserWishlist(String userId);

    int countItemWishlist(String itemId);
}
