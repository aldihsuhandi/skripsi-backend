package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.foundation.model.result.TransactionDO;
import id.thesis.shumishumi.foundation.model.result.TransactionDetailDO;

import java.util.List;

public interface TransactionDAO {
    void createTransaction(TransactionDO transaction);

    void createTransactionDetail(TransactionDetailDO detailDO);

    TransactionDO queryTransaction(String transactionId);

    List<TransactionDO> queryList(String status);

    List<TransactionDO> queryList(String userId, String status, PagingContext pagingContext);

    List<TransactionDetailDO> queryDetailTransaction(String transactionId);

    void updateStatus(String transactionId, String transactionStatus);

    void update(TransactionDO transaction);

    void delete(String transactionId);
}
