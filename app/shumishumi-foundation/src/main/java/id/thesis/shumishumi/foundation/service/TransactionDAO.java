package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.result.TransactionDO;
import id.thesis.shumishumi.foundation.model.result.TransactionDetailDO;

public interface TransactionDAO {
    void createTransaction(TransactionDO transaction);

    void createTransactionDetail(TransactionDetailDO detailDO);
}
