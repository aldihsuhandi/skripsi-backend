/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.common.converter;

import id.thesis.shumishumi.common.model.request.item.CreateItemInnerRequest;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.rest.request.item.CreateItemRequest;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ItemRequestConverter.java, v 0.1 2023‐01‐16 5:05 PM Aldih Suhandi Exp $$
 */
public class ItemRequestConverter {
    public static CreateItemInnerRequest toInnerRequest(CreateItemRequest request, String merchantId) {
        if (request == null) {
            return null;
        }

        CreateItemInnerRequest innerRequest = new CreateItemInnerRequest();
        innerRequest.setItemId(FunctionUtil.generateUUID());
        innerRequest.setItemName(request.getItemName());
        innerRequest.setItemDescription(request.getItemDescription());
        innerRequest.setItemImages(request.getItemImages());
        innerRequest.setItemPrice(request.getItemPrice());
        innerRequest.setItemQuantity(request.getItemQuantity());
        innerRequest.setCategoryName(request.getCategoryName());
        innerRequest.setHobbyName(request.getHobbyName());
        innerRequest.setMerchantInterestLeve(request.getMerchantInterestLeve());
        innerRequest.setMerchantId(merchantId);

        return innerRequest;
    }
}
