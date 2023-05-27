package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;

import java.util.List;

public interface TransactionService {
    String create(TransactionVO transactionVO);

    TransactionVO query(String transactionId, boolean withDetail);

    List<TransactionVO> queryList(String userId, String status,
                                  PagingContext pagingContext);

    List<TransactionVO> queryList(String status);

    void update(TransactionVO transaction);

    void checkPaymentStatus(String transactionId);

    void changeStatus(String transactionId, String transactionStatus);
}
