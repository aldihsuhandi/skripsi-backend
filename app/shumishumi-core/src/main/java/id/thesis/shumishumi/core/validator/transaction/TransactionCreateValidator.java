package id.thesis.shumishumi.core.validator.transaction;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionCreateRequest;

public class TransactionCreateValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof TransactionCreateRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        TransactionCreateRequest request = (TransactionCreateRequest) baseRequest;
        if (!request.isFromCart()) {
            ParamChecker.isNotEmpty(request.getItems(), "items", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
            request.getItems().forEach(item -> {
                ParamChecker.isNotEmpty(item.getItemId(), "itemId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
                ParamChecker.isExpected(item.getQuantity() > 0, "quantity", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
            });
        }
    }
}
