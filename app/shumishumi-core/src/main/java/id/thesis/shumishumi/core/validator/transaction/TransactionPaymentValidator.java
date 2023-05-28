package id.thesis.shumishumi.core.validator.transaction;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionPaymentRequest;

public class TransactionPaymentValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof TransactionPaymentRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        TransactionPaymentRequest request = (TransactionPaymentRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getTransactionId(), "transactionId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isNotEmpty(request.getPaymentType(), "paymentType", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
