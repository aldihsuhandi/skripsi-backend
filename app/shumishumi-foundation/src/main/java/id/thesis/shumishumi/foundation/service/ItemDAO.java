package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.request.ItemDAORequest;
import id.thesis.shumishumi.foundation.model.result.HistoryItemDO;
import id.thesis.shumishumi.foundation.model.result.ItemDO;

import java.util.List;

public interface ItemDAO {
    List<ItemDO> queryAll(ItemDAORequest request);

    ItemDO queryById(ItemDAORequest request);

    List<ItemDO> query(ItemDAORequest request);

    int count();

    void create(ItemDO request);

    void update(ItemDO request);

    void updateImage(ItemDAORequest request);

    void approve(ItemDAORequest request);

    void updateUserLevel(String itemId, String interestLevelId);

    List<String> autocomplete(ItemDAORequest request);

    void createItemHistory(HistoryItemDO historyItemDO);

    HistoryItemDO queryItemHistory(String historyItemId);
}
