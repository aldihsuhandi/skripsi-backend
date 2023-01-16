package id.thesis.shumishumi.dalgen.service;

import id.thesis.shumishumi.dalgen.model.request.ItemDAORequest;
import id.thesis.shumishumi.dalgen.model.result.ItemDO;

import java.util.List;

public interface ItemDAO {
    List<ItemDO> queryAll(ItemDAORequest request);

    List<ItemDO> query(ItemDAORequest request);

    int count();

    void create(ItemDAORequest request);

    void update(ItemDAORequest request);
}
