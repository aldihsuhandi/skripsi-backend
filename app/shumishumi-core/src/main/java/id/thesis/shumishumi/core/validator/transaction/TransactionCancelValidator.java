package id.thesis.shumishumi.core.validator.transaction;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionCancelRequest;

public class TransactionCancelValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof TransactionCancelRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        TransactionCancelRequest request = (TransactionCancelRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getTransactionId(), "transactionId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
