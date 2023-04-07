package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.common.converter.ViewObjectConverter;
import id.thesis.shumishumi.common.model.viewobject.ItemWishlistVO;
import id.thesis.shumishumi.core.service.ItemWishlistService;
import id.thesis.shumishumi.foundation.dalgen.service.ItemWishlistDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemWishlistServiceImpl implements ItemWishlistService {

    @Autowired
    private ItemWishlistDAO itemWishlistDAO;

    @Override
    public void addWishlist(String userId, String itemId) {
        itemWishlistDAO.addWishlist(userId, itemId);
    }

    @Override
    public void removeWishlist(String userId, String itemId) {
        itemWishlistDAO.removeWishlist(userId, itemId);
    }

    @Override
    public List<ItemWishlistVO> queryUserWishlist(String userId) {
        return itemWishlistDAO.queryUserWishlist(userId).stream().
                map(ViewObjectConverter::toViewObject).collect(Collectors.toList());
    }

    @Override
    public int countItemWishlist(String itemId) {
        return itemWishlistDAO.countItemWishlist(itemId);
    }
}
