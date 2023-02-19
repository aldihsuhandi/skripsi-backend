package id.thesis.shumishumi.service.service;

import id.thesis.shumishumi.common.model.viewobject.ItemImageVO;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.converter.ViewObjectConverter;
import id.thesis.shumishumi.core.service.ItemImageService;
import id.thesis.shumishumi.foundation.model.request.ItemImageDAORequest;
import id.thesis.shumishumi.foundation.service.ItemImageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ItemImageServiceImpl implements ItemImageService {

    @Autowired
    private ItemImageDAO itemImageDAO;

    @Override
    public void create(List<Blob> image, String itemId) {
        if (image == null || image.isEmpty()) {
            return;
        }

        image.forEach(img -> {
            String itemImageId = FunctionUtil.generateUUID();
            ItemImageDAORequest daoRequest = new ItemImageDAORequest();
            daoRequest.setItemImage(img);
            daoRequest.setItemId(itemId);
            daoRequest.setItemImageId(itemImageId);

            itemImageDAO.create(daoRequest);
        });
    }

    @Override
    public List<ItemImageVO> queryByItemId(String itemId) {
        return itemImageDAO.queryByItemId(itemId).stream().
                map(ViewObjectConverter::toViewObject).collect(Collectors.toList());
    }

    @Override
    public void delete(List<String> imageIds) {
        if (imageIds == null || imageIds.isEmpty()) {
            return;
        }

        StringBuilder idsBuilder = new StringBuilder();
        boolean needComma = false;
        for (String imageId : imageIds) {
            if (needComma) {
                idsBuilder.append(",");
            }
            idsBuilder.append(imageId);
            needComma = true;
        }

        ItemImageDAORequest daoRequest = new ItemImageDAORequest();
        daoRequest.setItemImageId(idsBuilder.toString());
        itemImageDAO.softDelete(daoRequest);
    }
}