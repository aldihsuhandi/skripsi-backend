/**
 *
 */
package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.context.ItemFilterContext;
import id.thesis.shumishumi.common.model.context.ItemUpdateContext;
import id.thesis.shumishumi.common.model.request.item.CreateItemInnerRequest;
import id.thesis.shumishumi.common.model.viewobject.ItemVO;

import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ItemSerivce.java, v 0.1 2023‐01‐16 5:01 PM Aldih Suhandi Exp $$
 */
public interface ItemService {
    void create(CreateItemInnerRequest request);

    ItemVO queryById(String itemId, boolean useCache);

    List<ItemVO> queryList(ItemFilterContext itemFilterContext,
                           int page, int numberOfItem, boolean useCache);

    void update(ItemVO itemVO, ItemUpdateContext itemUpdateContext);

    void updatePicture(String itemId, List<String> itemImages);

    void approveItem(String itemId);

    void refreshCache(List<String> itemIds, boolean refreshAll);

    int count(ItemFilterContext itemFilterContext, boolean useCache);

    List<String> autocomplete(String itemName, boolean useCache);
}
