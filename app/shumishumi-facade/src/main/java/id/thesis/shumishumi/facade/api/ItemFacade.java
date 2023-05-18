/**
 *
 */
package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.item.AutocompleteItemRequest;
import id.thesis.shumishumi.facade.request.item.CreateItemRequest;
import id.thesis.shumishumi.facade.request.item.ItemApprovalRequest;
import id.thesis.shumishumi.facade.request.item.QueryItemDetailRequest;
import id.thesis.shumishumi.facade.request.item.QueryItemRequest;
import id.thesis.shumishumi.facade.request.item.RecommendRequest;
import id.thesis.shumishumi.facade.request.item.UpdateItemRequest;
import id.thesis.shumishumi.facade.result.item.AutocompleteItemResult;
import id.thesis.shumishumi.facade.result.item.CreateItemResult;
import id.thesis.shumishumi.facade.result.item.ItemApprovalResult;
import id.thesis.shumishumi.facade.result.item.QueryItemDetailResult;
import id.thesis.shumishumi.facade.result.item.QueryItemResult;
import id.thesis.shumishumi.facade.result.item.RecommendResult;
import id.thesis.shumishumi.facade.result.item.UpdateItemResult;


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
