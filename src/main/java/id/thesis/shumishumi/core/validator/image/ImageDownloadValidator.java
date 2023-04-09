package id.thesis.shumishumi.core.validator.image;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.image.ImageDownloadRequest;
import id.thesis.shumishumi.core.validator.BaseValidator;

public class ImageDownloadValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof ImageDownloadRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ImageDownloadRequest request = (ImageDownloadRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getImageId(), "imageId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
