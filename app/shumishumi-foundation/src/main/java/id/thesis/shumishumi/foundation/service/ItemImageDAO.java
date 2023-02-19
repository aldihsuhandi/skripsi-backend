package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.request.ItemImageDAORequest;
import id.thesis.shumishumi.foundation.model.result.ItemImageDO;

import java.util.List;

public interface ItemImageDAO {
    List<ItemImageDO> queryByItemId(String itemId);

    void create(ItemImageDAORequest request);

    void softDelete(ItemImageDAORequest request);
}
