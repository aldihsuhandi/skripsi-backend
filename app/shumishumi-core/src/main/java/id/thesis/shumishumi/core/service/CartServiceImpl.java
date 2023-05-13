package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.service.CartService;
import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.core.converter.ViewObjectConverter;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.viewobject.CartVO;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.foundation.model.result.CartDO;
import id.thesis.shumishumi.foundation.service.CartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private ItemService itemService;

    @Override
    public void addToCart(String userId, String itemId, int quantity) {
        cartDAO.create(userId, itemId, quantity);
    }

    @Override
    public void update(String userId, String itemId, int quantity) {
        cartDAO.update(userId, itemId, quantity);
    }

    @Override
    public CartVO queryItemInCart(String userId, String itemId) {
        CartDO cartDO = cartDAO.query(userId, itemId);
        ItemVO itemVO = itemService.queryById(cartDO.getPk().getItemId(), true);

        return ViewObjectConverter.toViewObject(cartDO, itemVO);
    }

    @Override
    public List<CartVO> queryCartList(String userId, PagingContext pagingContext) {
        return cartDAO.queryList(userId, pagingContext).stream().map(cartDO -> {
            ItemVO item = itemService.queryById(cartDO.getPk().getItemId(), true);
            return ViewObjectConverter.toViewObject(cartDO, item);
        }).collect(Collectors.toList());
    }

    @Override
    public Long calculatePrice(String userId) {
        Long price = 0L;
        List<CartDO> carts = cartDAO.queryAll(userId);

        for (CartDO cart : carts) {
            ItemVO item = itemService.queryById(cart.getPk().getItemId(), true);
            price = item.getItemPrice() * cart.getQuantity();
        }

        return price;
    }
}
