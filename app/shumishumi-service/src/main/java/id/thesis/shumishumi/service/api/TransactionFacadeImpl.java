package id.thesis.shumishumi.service.api;

import id.thesis.shumishumi.core.callback.ProcessCallback;
import id.thesis.shumishumi.core.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.facade.api.TransactionFacade;
import id.thesis.shumishumi.facade.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.facade.request.transaction.TransactionCreateRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionPaymentRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionQueryDetailRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionCreateResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionPaymentResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionQueryDetailResult;
import org.springframework.stereotype.Service;

@Service
public class TransactionFacadeImpl extends ProcessFacade implements TransactionFacade {
    @Override
    public TransactionCreateResult create(TransactionCreateRequest request) {
        return (TransactionCreateResult) ProcessCallbackSupport.process(ProcessTypeEnum.TRANSACTION_CREATE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new TransactionCreateResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public TransactionPaymentResult payment(TransactionPaymentRequest request) {
        return (TransactionPaymentResult) ProcessCallbackSupport.process(ProcessTypeEnum.TRANSACTION_PAYMENT, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new TransactionPaymentResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public TransactionQueryDetailResult queryDetail(TransactionQueryDetailRequest request) {
        return (TransactionQueryDetailResult) ProcessCallbackSupport.process(ProcessTypeEnum.TRANSACTION_DETAIL,
                request, new ProcessCallback() {
                    @Override
                    public BaseResult initResult() {
                        return new TransactionQueryDetailResult();
                    }

                    @Override
                    public void process(ProcessTypeEnum processType, BaseResult result) {
                        doProcess(request, result, processType);
                    }
                });
    }
}
