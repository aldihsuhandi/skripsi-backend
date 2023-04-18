package id.thesis.shumishumi.core.processor.image;

import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.core.service.ImageService;
import id.thesis.shumishumi.facade.model.viewobject.ImageVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.image.ImageUploadRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.image.ImageUploadResult;
import org.springframework.beans.factory.annotation.Autowired;

public class ImageUploadProcessor implements BaseProcessor {

    @Autowired
    private ImageService imageService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        ImageUploadResult result = (ImageUploadResult) baseResult;
        ImageUploadRequest request = (ImageUploadRequest) baseRequest;

        ImageVO imageVO = new ImageVO(request.getImage());
        imageService.upload(imageVO);

        result.setImageId(imageVO.getImageId());
    }
}
