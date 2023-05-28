package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.transaction.TransactionCancelRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionCreateRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionFinishRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionPaymentRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionQueryDetailRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionQueryRequest;
import id.thesis.shumishumi.facade.result.transaction.TransactionCancelResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionCreateResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionFinishResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionPaymentResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionQueryDetailResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionQueryResult;

public interface TransactionFacade {
    TransactionCreateResult create(TransactionCreateRequest request);

    TransactionPaymentResult payment(TransactionPaymentRequest request);

    TransactionQueryDetailResult queryDetail(TransactionQueryDetailRequest request);

    TransactionQueryResult query(TransactionQueryRequest request);

    TransactionFinishResult finish(TransactionFinishRequest request);

    TransactionCancelResult cancel(TransactionCancelRequest request);
}
