package id.thesis.shumishumi.core.validator.item.image;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.item.image.ItemImageRemoveRequest;
import id.thesis.shumishumi.core.validator.BaseValidator;

public class ItemRemoveImageValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof ItemImageRemoveRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ItemImageRemoveRequest request = (ItemImageRemoveRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getItemId(), "itemId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isNotNull(request.getImageIds(), "imageIds", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
