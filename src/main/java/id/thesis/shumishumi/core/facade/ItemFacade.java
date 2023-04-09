/**
 *
 */
package id.thesis.shumishumi.core.facade;

import id.thesis.shumishumi.core.request.item.AutocompleteItemRequest;
import id.thesis.shumishumi.core.request.item.CreateItemRequest;
import id.thesis.shumishumi.core.request.item.ItemApprovalRequest;
import id.thesis.shumishumi.core.request.item.QueryItemRequest;
import id.thesis.shumishumi.core.request.item.RecommendRequest;
import id.thesis.shumishumi.core.request.item.UpdateItemRequest;
import id.thesis.shumishumi.core.result.item.AutocompleteItemResult;
import id.thesis.shumishumi.core.result.item.CreateItemResult;
import id.thesis.shumishumi.core.result.item.ItemApprovalResult;
import id.thesis.shumishumi.core.result.item.QueryItemResult;
import id.thesis.shumishumi.core.result.item.RecommendResult;
import id.thesis.shumishumi.core.result.item.UpdateItemResult;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ItemFacade.java, v 0.1 2023‐01‐16 4:21 PM Aldih Suhandi Exp $$
 */
public interface ItemFacade {
    CreateItemResult create(CreateItemRequest request);

    QueryItemResult query(QueryItemRequest request);

    UpdateItemResult update(UpdateItemRequest request);

    @Deprecated
    ItemApprovalResult approve(ItemApprovalRequest request);

    RecommendResult recommend(RecommendRequest request);

    AutocompleteItemResult autocomplete(AutocompleteItemRequest request);
}
