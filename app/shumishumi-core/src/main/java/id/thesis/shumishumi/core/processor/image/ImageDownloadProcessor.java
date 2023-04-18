package id.thesis.shumishumi.core.processor.image;

import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.core.service.ImageService;
import id.thesis.shumishumi.facade.model.context.BlobMultipartFile;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.ImageVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.image.ImageDownloadRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.image.ImageDownloadResult;
import org.springframework.beans.factory.annotation.Autowired;

public class ImageDownloadProcessor implements BaseProcessor {

    @Autowired
    private ImageService imageService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        ImageDownloadResult result = (ImageDownloadResult) baseResult;
        ImageDownloadRequest request = (ImageDownloadRequest) baseRequest;

        ImageVO imageVO = imageService.download(request.getImageId());
        AssertUtil.isNotNull(imageVO, ShumishumiErrorCodeEnum.IMAGE_NOT_FOUND.getErrorMsg(), ShumishumiErrorCodeEnum.IMAGE_NOT_FOUND);

        BlobMultipartFile image = new BlobMultipartFile(imageVO.getImageName(), imageVO.getImageExt(), imageVO.getImage());
        result.setImage(image);
    }
}
