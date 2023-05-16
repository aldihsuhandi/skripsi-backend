package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.CartDO;
import id.thesis.shumishumi.foundation.model.result.primarykey.CartDOPK;
import id.thesis.shumishumi.foundation.repository.CartRepository;
import id.thesis.shumishumi.foundation.service.CartDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDAOImpl implements CartDAO {

    @Autowired
    private CartRepository cartRepository;

    private final static Logger LOGGER = LoggerFactory.getLogger(LogConstant.DALGEN_LOGGER);

    @Override
    public void create(String userId, String itemId, int quantity) {
        LogUtil.info(LOGGER, String.format("cartDAO#create[userId=%s,itemId=%s,quantity=%d]", userId, itemId, quantity));
        CartDO cartDO = new CartDO();
        cartDO.setPk(new CartDOPK(userId, itemId));
        cartDO.setQuantity(quantity);

        try {
            cartRepository.save(cartDO);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void update(String userId, String itemId, int quantity) {
        LogUtil.info(LOGGER, String.format("cartDAO#update[userId=%s,itemId=%s,quantity=%d]", userId, itemId, quantity));
        CartDO cartDO = new CartDO();
        cartDO.setPk(new CartDOPK(userId, itemId));
        cartDO.setQuantity(quantity);

        try {
            cartRepository.save(cartDO);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void delete(String userId, String itemId) {
        LogUtil.info(LOGGER, String.format("cartDAO#delete[userId=%s,itemId=%s]", userId, itemId));

        try {
            cartRepository.deleteById(new CartDOPK(userId, itemId));
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public CartDO query(String userId, String itemId) {
        LogUtil.info(LOGGER, String.format("cartDAO#query[userId=%s,itemId=%s]", userId, itemId));

        CartDO cartDO;
        try {
            cartDO = cartRepository.findById(new CartDOPK(userId, itemId)).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("cartDAO#query[cartDO=%s]", cartDO));
        return cartDO;
    }

    @Override
    public List<CartDO> queryList(String userId, PagingContext pagingContext) {
        LogUtil.info(LOGGER, String.format("cartDAO#queryList[userId=%s,pagingContext=%s]", userId, pagingContext));
        List<CartDO> carts;

        Pageable pageable = PageRequest.of(pagingContext.getPageNumber() - 1, pagingContext.getNumberOfItem());

        try {
            Page<CartDO> pageCart = cartRepository.findByPkUserId(userId, pageable);
            pagingContext.setTotalItem(pageCart.getTotalElements());
            carts = pageCart.getContent();
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("cartDAO#queryList[carts=%s]", carts));
        return carts;
    }

    @Override
    public List<CartDO> queryAll(String userId) {
        LogUtil.info(LOGGER, String.format("cartDAO#queryAll[userId=%s]", userId));
        List<CartDO> carts;

        try {
            carts = cartRepository.findByPkUserId(userId);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("cartDAO#queryAll[carts=%s]", carts));
        return carts;
    }
}
