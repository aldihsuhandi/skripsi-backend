package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.transaction.TransactionCreateRequest;
import id.thesis.shumishumi.facade.result.transaction.TransactionCreateResult;

public interface TransactionFacade {
    TransactionCreateResult create(TransactionCreateRequest request);
}
