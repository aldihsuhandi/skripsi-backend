package id.thesis.shumishumi.core.validator.image;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.image.ImageDownloadRequest;

public class ImageDownloadValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof ImageDownloadRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ImageDownloadRequest request = (ImageDownloadRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getImageId(), "imageId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
