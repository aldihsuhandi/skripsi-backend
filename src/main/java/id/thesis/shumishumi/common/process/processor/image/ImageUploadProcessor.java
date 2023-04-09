package id.thesis.shumishumi.common.process.processor.image;

import id.thesis.shumishumi.common.model.viewobject.ImageVO;
import id.thesis.shumishumi.common.process.processor.BaseProcessor;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.image.ImageUploadRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.result.image.ImageUploadResult;
import id.thesis.shumishumi.core.service.ImageService;
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
