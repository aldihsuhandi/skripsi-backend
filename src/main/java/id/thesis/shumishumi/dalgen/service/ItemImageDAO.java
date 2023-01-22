package id.thesis.shumishumi.dalgen.service;

import id.thesis.shumishumi.dalgen.model.request.ItemImageDAORequest;
import id.thesis.shumishumi.dalgen.model.result.ItemImageDO;

import java.util.List;

public interface ItemImageDAO {
    List<ItemImageDO> queryByItemId(String itemId);

    void create(ItemImageDAORequest request);

    void softDelete(ItemImageDAORequest request);
}
