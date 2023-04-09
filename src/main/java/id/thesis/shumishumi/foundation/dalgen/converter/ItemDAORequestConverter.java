/**
 *
 */
package id.thesis.shumishumi.foundation.dalgen.converter;

import id.thesis.shumishumi.common.model.context.ItemFilterContext;
import id.thesis.shumishumi.common.model.context.ItemUpdateContext;
import id.thesis.shumishumi.common.model.request.item.CreateItemInnerRequest;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.common.util.constant.CommonConst;
import id.thesis.shumishumi.foundation.dalgen.model.request.ItemDAORequest;

import java.util.Date;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ItemDAORequestConverter.java, v 0.1 2023‐01‐18 11:53 Aldih Suhandi Exp $$
 */
public class ItemDAORequestConverter {
    public static ItemDAORequest toDAORequest(CreateItemInnerRequest request) {

        StringBuilder imageBuilder = new StringBuilder();
        request.getItemImages().forEach(image ->
                imageBuilder.append(image).append(CommonConst.SEPARATOR));
        String imageStr = "";
        if (imageBuilder.length() != 0) {
            imageStr = imageBuilder.substring(0, imageBuilder.length() - 1);
        }

        ItemDAORequest itemDAORequest = new ItemDAORequest();
        itemDAORequest.setItemId(FunctionUtil.generateUUID());
        itemDAORequest.setItemName(request.getItemName());
        itemDAORequest.setItemImages(imageStr);
        itemDAORequest.setItemDescription(request.getItemDescription());
        itemDAORequest.setItemPrice(request.getItemPrice());
        itemDAORequest.setItemQuantity(request.getItemQuantity());
        itemDAORequest.setCategoryId(request.getCategoryId());
        itemDAORequest.setHobbyId(request.getHobbyId());
        itemDAORequest.setMerchantLevelId(request.getMerchantLevelId());
        itemDAORequest.setMerchantId(request.getMerchantId());
        itemDAORequest.setApproved(true);

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
        itemDAORequest.setDeleted(filterContext.isDeleted());
        itemDAORequest.setApproved(filterContext.isApproved());

        return itemDAORequest;
    }

    public static ItemDAORequest toDAORequest(ItemUpdateContext updateContext, String categoryId,
                                              String hobbyId, String merchantLevelId, String itemId) {
        ItemDAORequest itemDAORequest = new ItemDAORequest();
        itemDAORequest.setItemId(itemId);
        itemDAORequest.setItemName(updateContext.getItemName());
        itemDAORequest.setItemPrice(updateContext.getItemPrice());
        itemDAORequest.setItemDescription(updateContext.getItemDescription());
        itemDAORequest.setItemQuantity(updateContext.getItemQuantity());
        itemDAORequest.setCategoryId(categoryId);
        itemDAORequest.setHobbyId(hobbyId);
        itemDAORequest.setMerchantLevelId(merchantLevelId);
        itemDAORequest.setGmtModified(new Date());

        return itemDAORequest;
    }

    public static ItemDAORequest toDAORequest(String itemId) {
        ItemDAORequest itemDAORequest = new ItemDAORequest();
        itemDAORequest.setItemId(itemId);
        itemDAORequest.setGmtModified(new Date());

        return itemDAORequest;
    }
}
