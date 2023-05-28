package id.thesis.shumishumi.core.validator.transaction;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionQueryDetailRequest;

public class TransactionQueryDetailValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof TransactionQueryDetailRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        TransactionQueryDetailRequest request = (TransactionQueryDetailRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getTransactionId(), "transaction id", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
