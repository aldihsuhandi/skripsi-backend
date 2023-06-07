package id.thesis.shumishumi.core.validator.cart;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.cart.CartSelectRequest;

public class CartSelectValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof CartSelectRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        CartSelectRequest request = (CartSelectRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getItemIds(), "item id", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
