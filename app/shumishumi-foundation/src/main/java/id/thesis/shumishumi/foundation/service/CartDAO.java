package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.foundation.model.result.CartDO;

import java.util.List;

public interface CartDAO {
    void create(String userId, String itemId, int quantity);

    void update(CartDO cart);

    void delete(String userId, String itemId);

    CartDO query(String userId, String itemId);

    List<CartDO> queryList(String userId, PagingContext pagingContext);

    List<CartDO> queryAll(String userId);

    List<CartDO> queryAllSelected(String userId);
}
