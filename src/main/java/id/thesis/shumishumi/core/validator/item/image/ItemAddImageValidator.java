package id.thesis.shumishumi.core.validator.item.image;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.item.image.ItemImageAddRequest;
import id.thesis.shumishumi.core.validator.BaseValidator;

public class ItemAddImageValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof ItemImageAddRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ItemImageAddRequest request = (ItemImageAddRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getItemId(), "itemId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isNotNull(request.getImage(), "image", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
