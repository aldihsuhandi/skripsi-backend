package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.transaction.TransactionCreateRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionPaymentRequest;
import id.thesis.shumishumi.facade.result.transaction.TransactionCreateResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionPaymentResult;

public interface TransactionFacade {
    TransactionCreateResult create(TransactionCreateRequest request);

    TransactionPaymentResult payment(TransactionPaymentRequest request);
}
