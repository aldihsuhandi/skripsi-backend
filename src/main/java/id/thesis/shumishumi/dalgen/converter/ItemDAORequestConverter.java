/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.dalgen.converter;

import id.thesis.shumishumi.common.model.context.ItemFilterContext;
import id.thesis.shumishumi.common.model.request.item.CreateItemInnerRequest;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.dalgen.model.request.ItemDAORequest;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ItemDAORequestConverter.java, v 0.1 2023‐01‐18 11:53 Aldih Suhandi Exp $$
 */
public class ItemDAORequestConverter {
    public static ItemDAORequest toDAORequest(CreateItemInnerRequest request) {
        ItemDAORequest itemDAORequest = new ItemDAORequest();
        itemDAORequest.setItemId(FunctionUtil.generateUUID());
        itemDAORequest.setItemName(request.getItemName());
        itemDAORequest.setItemDescription(request.getItemDescription());
        itemDAORequest.setItemPrice(request.getItemPrice());
        itemDAORequest.setItemQuantity(request.getItemQuantity());
        itemDAORequest.setCategoryId(request.getCategoryId());
        itemDAORequest.setHobbyId(request.getHobbyId());
        itemDAORequest.setMerchantLevelId(request.getMerchantLevelId());
        itemDAORequest.setMerchantId(request.getMerchantId());

        return itemDAORequest;
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

        return itemDAORequest;
    }
}
