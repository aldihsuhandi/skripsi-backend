/**
 * 
 *
 */
package id.thesis.shumishumi.core.facade;

import id.thesis.shumishumi.rest.request.item.CreateItemRequest;
import id.thesis.shumishumi.rest.request.item.ItemApprovalRequest;
import id.thesis.shumishumi.rest.request.item.QueryItemRequest;
import id.thesis.shumishumi.rest.request.item.RecommendRequest;
import id.thesis.shumishumi.rest.request.item.UpdateItemRequest;
import id.thesis.shumishumi.rest.result.item.CreateItemResult;
import id.thesis.shumishumi.rest.result.item.ItemApprovalResult;
import id.thesis.shumishumi.rest.result.item.QueryItemResult;
import id.thesis.shumishumi.rest.result.item.RecommendResult;
import id.thesis.shumishumi.rest.result.item.UpdateItemResult;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ItemFacade.java, v 0.1 2023‐01‐16 4:21 PM Aldih Suhandi Exp $$
 */
public interface ItemFacade {
    CreateItemResult create(CreateItemRequest request);

    QueryItemResult query(QueryItemRequest request);

    UpdateItemResult update(UpdateItemRequest request);

    ItemApprovalResult approve(ItemApprovalRequest request);

    RecommendResult recommend(RecommendRequest request);
}
