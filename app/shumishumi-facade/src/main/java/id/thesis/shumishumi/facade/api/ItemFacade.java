/**
 *
 */
package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.item.*;
import id.thesis.shumishumi.facade.result.item.*;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ItemFacade.java, v 0.1 2023‐01‐16 4:21 PM Aldih Suhandi Exp $$
 */
public interface ItemFacade {
    CreateItemResult create(CreateItemRequest request);

    QueryItemResult query(QueryItemRequest request);

    QueryItemDetailResult queryDetail(QueryItemDetailRequest request);

    UpdateItemResult update(UpdateItemRequest request);

    @Deprecated
    ItemApprovalResult approve(ItemApprovalRequest request);

    RecommendResult recommend(RecommendRequest request);

    AutocompleteItemResult autocomplete(AutocompleteItemRequest request);
}
