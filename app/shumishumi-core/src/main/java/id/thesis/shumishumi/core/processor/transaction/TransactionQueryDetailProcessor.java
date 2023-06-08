package id.thesis.shumishumi.core.processor.transaction;

import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.TransactionService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.converter.SummaryConverter;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.enumeration.TransactionStatusEnum;
import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionQueryDetailRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionQueryDetailResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionQueryDetailProcessor implements BaseProcessor {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private SessionService sessionService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        TransactionQueryDetailRequest request = (TransactionQueryDetailRequest) baseRequest;
        TransactionQueryDetailResult result = (TransactionQueryDetailResult) baseResult;

        String userId = sessionService.query(request.getSessionId()).getUserId();

        TransactionVO transaction = transactionService.query(request.getTransactionId(), false);
        AssertUtil.isNotNull(transaction, "transaction not found", ShumishumiErrorCodeEnum.TRANSACTION_NOT_FOUND);
        AssertUtil.isExpected(transaction.getUserId(), userId,
                "this transaction is not from this user", ShumishumiErrorCodeEnum.USER_INVALID);

        if (!StringUtils.equals(transaction.getStatus(), TransactionStatusEnum.DONE.getCode())) {
            transactionService.checkPaymentStatus(transaction.getTransactionId());
        }

        transaction = transactionService.query(request.getTransactionId(), true);

        result.setTransaction(SummaryConverter.toSummary(transaction));
    }
}
