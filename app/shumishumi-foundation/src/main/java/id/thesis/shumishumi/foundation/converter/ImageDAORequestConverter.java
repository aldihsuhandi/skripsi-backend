package id.thesis.shumishumi.foundation.converter;


import id.thesis.shumishumi.facade.model.viewobject.ImageVO;
import id.thesis.shumishumi.foundation.model.result.ImageDO;

public class ImageDAORequestConverter {
    public static ImageDO toDAORequest(ImageVO imageVO) {
        if (imageVO == null) {
            return null;
        }

        ImageDO request = new ImageDO();
        request.setImageId(imageVO.getImageId());
        request.setImage(imageVO.getImage());
        request.setImageExt(imageVO.getImageExt());
        request.setImageName(imageVO.getImageName());

        return request;
    }
}
