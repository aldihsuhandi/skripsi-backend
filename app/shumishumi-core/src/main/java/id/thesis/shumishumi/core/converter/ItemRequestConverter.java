/**
 *
 */
package id.thesis.shumishumi.core.converter;

import id.thesis.shumishumi.common.model.request.item.CreateItemInnerRequest;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.facade.request.item.CreateItemRequest;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ItemRequestConverter.java, v 0.1 2023‐01‐16 5:05 PM Aldih Suhandi Exp $$
 */
public class ItemRequestConverter {
    public static CreateItemInnerRequest toInnerRequest(CreateItemRequest request, String merchantId, String categoryId,
                                                        String hobbyId, String merchantLevelId, String postId) {
        if (request == null) {
            return null;
        }

        CreateItemInnerRequest innerRequest = new CreateItemInnerRequest();
        innerRequest.setItemId(FunctionUtil.generateUUID());
        innerRequest.setItemName(request.getItemName());
        innerRequest.setItemImages(request.getItemImages());
        innerRequest.setItemDescription(request.getItemDescription());
        innerRequest.setItemPrice(request.getItemPrice());
        innerRequest.setItemQuantity(request.getItemQuantity());
        innerRequest.setCategoryId(categoryId);
        innerRequest.setHobbyId(hobbyId);
        innerRequest.setMerchantLevelId(merchantLevelId);
        innerRequest.setMerchantId(merchantId);
        innerRequest.setPostId(postId);

        return innerRequest;
    }
}
