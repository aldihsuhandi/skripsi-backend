package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.ItemWishlistDO;
import id.thesis.shumishumi.foundation.model.result.primarykey.ItemWishlistDOPK;
import id.thesis.shumishumi.foundation.repository.ItemWishlistRepository;
import id.thesis.shumishumi.foundation.service.ItemWishlistDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemWishlistDAOImpl implements ItemWishlistDAO {

    private static final Logger DALGEN = LoggerFactory.getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private ItemWishlistRepository itemWishlistRepository;

    @Override
    public void addWishlist(String userId, String itemId) {
        LogUtil.info(DALGEN, String.format("itemWishlistDAO#addWishlist[userId=%s,itemId=%s]", userId, itemId));

        ItemWishlistDO wishlist = new ItemWishlistDO();
        wishlist.setPk(new ItemWishlistDOPK(itemId, userId));

        try {
            itemWishlistRepository.save(wishlist);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void removeWishlist(String userId, String itemId) {
        LogUtil.info(DALGEN, String.format("itemWishlistDAO#removeWishlist[userId=%s,itemId=%s]", userId, itemId));

        ItemWishlistDO wishlist = new ItemWishlistDO();
        wishlist.setPk(new ItemWishlistDOPK(itemId, userId));
        try {
            itemWishlistRepository.delete(wishlist);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public List<ItemWishlistDO> queryUserWishlist(String userId) {
        LogUtil.info(DALGEN, String.format("itemWishlistDAO#queryUserWishlist[userId=%s]", userId));

        List<ItemWishlistDO> result;
        try {
            result = itemWishlistRepository.findByUserId(userId);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN, String.format("itemWishlistDAO#queryUserWishlist[result=%s]", result.toString()));

        return result;
    }

    @Override
    public int countItemWishlist(String itemId) {
        LogUtil.info(DALGEN, String.format("itemWishlistDAO#countItemWishlist[itemId=%s]", itemId));

        int result = 0;
        try {
            result = itemWishlistRepository.countByItemId(itemId);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN, String.format("itemWishlistDAO#countItemWishlist[result=%s]", result));

        return result;
    }
}
