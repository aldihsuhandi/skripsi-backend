package id.thesis.shumishumi.core.validator.cart;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.cart.CartAddRequest;

public class CartAddValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof CartAddRequest, "requset", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        CartAddRequest request = (CartAddRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getItemId(), "itemId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ParamChecker.isNotNull(request.getQuantity(), "quantity", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(request.getQuantity() > 0, "quantity", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
