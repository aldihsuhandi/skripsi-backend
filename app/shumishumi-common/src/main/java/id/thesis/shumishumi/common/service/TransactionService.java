package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;

public interface TransactionService {
    String create(TransactionVO transactionVO);

    TransactionVO query(String transactionId, boolean withDetail);

    void update(TransactionVO transaction);

    void checkPaymentStatus(String transactionId);

    void changeStatus(String transactionId, String transactionStatus);
}
