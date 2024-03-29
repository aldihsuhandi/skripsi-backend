package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.service.ImageService;
import id.thesis.shumishumi.core.converter.ViewObjectConverter;
import id.thesis.shumishumi.facade.model.viewobject.ImageVO;
import id.thesis.shumishumi.foundation.converter.ImageDAORequestConverter;
import id.thesis.shumishumi.foundation.model.request.ImageDAORequest;
import id.thesis.shumishumi.foundation.service.ImageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDAO imageDAO;

    @Override
    public ImageVO download(String imageId) {
        ImageDAORequest request = new ImageDAORequest();
        request.setImageId(imageId);

        return ViewObjectConverter.toViewObject(imageDAO.query(request));
    }

    @Override
    public void upload(ImageVO imageVO) {
        imageDAO.insert(ImageDAORequestConverter.toDAORequest(imageVO));
    }
}
