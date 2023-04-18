package id.thesis.shumishumi.foundation.converter;


import id.thesis.shumishumi.facade.model.viewobject.ImageVO;
import id.thesis.shumishumi.foundation.model.request.ImageDAORequest;

public class ImageDAORequestConverter {
    public static ImageDAORequest toDAORequest(ImageVO imageVO) {
        if (imageVO == null) {
            return null;
        }

        ImageDAORequest request = new ImageDAORequest();
        request.setImageId(imageVO.getImageId());
        request.setImage(imageVO.getImage());
        request.setImageExt(imageVO.getImageExt());
        request.setImageName(imageVO.getImageName());

        return request;
    }
}
