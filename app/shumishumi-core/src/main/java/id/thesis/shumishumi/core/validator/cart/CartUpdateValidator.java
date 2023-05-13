package id.thesis.shumishumi.core.validator.cart;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.cart.CartUpdateRequest;

public class CartUpdateValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof CartUpdateRequest, "requset", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        CartUpdateRequest request = (CartUpdateRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getItemId(), "itemId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isNotNull(request.getQuantity(), "quantity", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
