package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.viewobject.CartVO;

import java.util.List;

public interface CartService {
    void addToCart(String userId, String itemId, int quantity);

    void update(String userId, String itemId, int quantity);

    CartVO queryItemInCart(String userId, String itemId);

    List<CartVO> queryCartList(String userId, PagingContext pagingContext);

    List<CartVO> queryCartSelectedList(String userId);

    void selectCart(List<String> itemIds, String userId, boolean selected);

    Long calculatePrice(String userId);
}
