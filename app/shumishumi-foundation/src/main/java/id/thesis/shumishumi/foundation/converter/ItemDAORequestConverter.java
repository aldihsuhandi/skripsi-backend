/**
 *
 */
package id.thesis.shumishumi.foundation.converter;


import id.thesis.shumishumi.common.model.request.item.CreateItemInnerRequest;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.context.ItemFilterContext;
import id.thesis.shumishumi.facade.model.context.ItemUpdateContext;
import id.thesis.shumishumi.foundation.model.request.ItemDAORequest;
import id.thesis.shumishumi.foundation.model.result.ItemDO;

import java.util.Date;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ItemDAORequestConverter.java, v 0.1 2023‐01‐18 11:53 Aldih Suhandi Exp $$
 */
public class ItemDAORequestConverter {
    public static ItemDO toDAORequest(CreateItemInnerRequest request) {

        StringBuilder imageBuilder = new StringBuilder();
        request.getItemImages().forEach(image ->
                imageBuilder.append(image).append(CommonConst.SEPARATOR));
        String imageStr = "";
        if (imageBuilder.length() != 0) {
            imageStr = imageBuilder.substring(0, imageBuilder.length() - 1);
        }

        ItemDO item = new ItemDO();
        item.setItemId(FunctionUtil.generateUUID());
        item.setItemName(request.getItemName());
        item.setItemImages(imageStr);
        item.setItemDescription(request.getItemDescription());
        item.setItemPrice(request.getItemPrice());
        item.setItemQuantity(request.getItemQuantity());
        item.setCategoryId(request.getCategoryId());
        item.setHobbyId(request.getHobbyId());
        item.setMerchantLevelId(request.getMerchantLevelId());
        item.setMerchantId(request.getMerchantId());
        item.setApproved(true);

        return item;
    }

    public static ItemDAORequest toDAORequest(ItemFilterContext filterContext, String categoryId,
                                              String hobbyId, String merchantLevelId, String userLevelId) {
        ItemDAORequest itemDAORequest = new ItemDAORequest();
        itemDAORequest.setItemName(filterContext.getItemName());
        itemDAORequest.setCategoryId(categoryId);
        itemDAORequest.setHobbyId(hobbyId);
        itemDAORequest.setMerchantLevelId(merchantLevelId);
        itemDAORequest.setUserLevelId(userLevelId);
        itemDAORequest.setMinPrice(filterContext.getMinItemPrice());
        itemDAORequest.setMaxPrice(filterContext.getMaxItemPrice());
        itemDAORequest.setMerchantId(filterContext.getMerchantId());
        itemDAORequest.setDeleted(filterContext.isDeleted());
        itemDAORequest.setApproved(filterContext.isApproved());

        return itemDAORequest;
    }

    public static ItemDO toDAORequest(ItemUpdateContext updateContext, String categoryId,
                                      String hobbyId, String merchantLevelId, String itemId) {
        ItemDO itemDO = new ItemDO();
        itemDO.setItemId(itemId);
        itemDO.setItemName(updateContext.getItemName());
        itemDO.setItemPrice(updateContext.getItemPrice());
        itemDO.setItemDescription(updateContext.getItemDescription());
        itemDO.setItemQuantity(updateContext.getItemQuantity());
        itemDO.setCategoryId(categoryId);
        itemDO.setHobbyId(hobbyId);
        itemDO.setMerchantLevelId(merchantLevelId);
        itemDO.setGmtModified(new Date());

        return itemDO;
    }

    public static ItemDAORequest toDAORequest(String itemId) {
        ItemDAORequest itemDAORequest = new ItemDAORequest();
        itemDAORequest.setItemId(itemId);
        itemDAORequest.setGmtModified(new Date());

        return itemDAORequest;
    }
}
