package id.thesis.shumishumi.foundation.integration.midtrans;

import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;

public interface MidtransClient {
    void createPayment(TransactionVO transaction);
}
