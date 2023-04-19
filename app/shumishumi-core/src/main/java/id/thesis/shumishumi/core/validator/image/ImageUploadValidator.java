package id.thesis.shumishumi.core.validator.image;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.image.ImageUploadRequest;

public class ImageUploadValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof ImageUploadRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ImageUploadRequest request = (ImageUploadRequest) baseRequest;
        ParamChecker.isNotNull(request.getImage(), "imageId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
