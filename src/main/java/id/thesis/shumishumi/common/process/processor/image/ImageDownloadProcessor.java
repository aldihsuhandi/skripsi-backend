package id.thesis.shumishumi.common.process.processor.image;

import id.thesis.shumishumi.common.model.context.BlobMultipartFile;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.viewobject.ImageVO;
import id.thesis.shumishumi.common.process.processor.BaseProcessor;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.image.ImageDownloadRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.result.image.ImageDownloadResult;
import id.thesis.shumishumi.core.service.ImageService;
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
