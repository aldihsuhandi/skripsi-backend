package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.viewobject.ItemImageVO;

import java.sql.Blob;
import java.util.List;

public interface ItemImageService {
    void create(List<Blob> image, String itemId);

    List<ItemImageVO> queryByItemId(String itemId);

    void delete(List<String> imageIds);
}
