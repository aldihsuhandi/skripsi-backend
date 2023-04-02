package id.thesis.shumishumi.foundation.dalgen.service;

import id.thesis.shumishumi.foundation.dalgen.model.request.ItemDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.result.ItemDO;

import java.util.List;

public interface ItemDAO {
    List<ItemDO> queryAll(ItemDAORequest request);

    ItemDO queryById(ItemDAORequest request);

    List<ItemDO> query(ItemDAORequest request);

    int count();

    void create(ItemDAORequest request);

    void update(ItemDAORequest request);

    void approve(ItemDAORequest request);

    List<String> autocomplete(ItemDAORequest request);
}
