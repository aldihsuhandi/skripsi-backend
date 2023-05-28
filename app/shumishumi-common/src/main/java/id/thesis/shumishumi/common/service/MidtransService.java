package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.common.model.result.midtrans.MidtransChargeInnerResult;
import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;

public interface MidtransService {
    MidtransChargeInnerResult createPayment(TransactionVO transaction);

    void cancelPayment(String transactionId);

    String checkStatus(String transactionId);
}
