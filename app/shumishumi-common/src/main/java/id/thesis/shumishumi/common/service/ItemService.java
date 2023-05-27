/**
 *
 */
package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.common.model.request.item.CreateItemInnerRequest;
import id.thesis.shumishumi.facade.model.context.ItemFilterContext;
import id.thesis.shumishumi.facade.model.context.ItemUpdateContext;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.viewobject.HistoryItemVO;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;

import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ItemSerivce.java, v 0.1 2023‐01‐16 5:01 PM Aldih Suhandi Exp $$
 */
public interface ItemService {
    void create(CreateItemInnerRequest request);

    String createHistoryItem(ItemVO item);

    HistoryItemVO queryItemHistory(String historyItemId);

    ItemVO queryById(String itemId, boolean useCache);

    List<ItemVO> queryList(ItemFilterContext itemFilterContext, PagingContext pagingContext, boolean useCache);

    void update(ItemVO itemVO, ItemUpdateContext itemUpdateContext);

    void updatePicture(String itemId, List<String> itemImages);

    void approveItem(String itemId);

    void refreshCache(List<String> itemIds, boolean refreshAll);

    void clearCache();

    int count(ItemFilterContext itemFilterContext, boolean useCache);

    List<String> autocomplete(String itemName, boolean useCache);
}
