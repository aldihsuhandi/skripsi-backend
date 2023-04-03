package id.thesis.shumishumi.foundation.dalgen.service;

import id.thesis.shumishumi.foundation.dalgen.model.request.ItemImageDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.result.ItemImageDO;

import java.util.List;

public interface ItemImageDAO {
    List<ItemImageDO> queryByItemId(String itemId);

    void create(ItemImageDAORequest request);

    void softDelete(ItemImageDAORequest request);
}
