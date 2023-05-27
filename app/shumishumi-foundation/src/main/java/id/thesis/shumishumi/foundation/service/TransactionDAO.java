package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.result.TransactionDO;
import id.thesis.shumishumi.foundation.model.result.TransactionDetailDO;

import java.util.List;

public interface TransactionDAO {
    void createTransaction(TransactionDO transaction);

    void createTransactionDetail(TransactionDetailDO detailDO);

    TransactionDO queryTransaction(String transactionId);

    List<TransactionDetailDO> queryDetailTransaction(String transactionId);

    void updateStatus(String transactionId, String transactionStatus);

    void update(TransactionDO transaction);
}
