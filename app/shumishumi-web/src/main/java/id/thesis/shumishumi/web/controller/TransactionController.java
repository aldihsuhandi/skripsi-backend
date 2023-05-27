package id.thesis.shumishumi.web.controller;

import id.thesis.shumishumi.common.model.form.transaction.TransactionCreateForm;
import id.thesis.shumishumi.common.model.form.transaction.TransactionPaymentForm;
import id.thesis.shumishumi.core.callback.ControllerCallback;
import id.thesis.shumishumi.core.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.facade.api.TransactionFacade;
import id.thesis.shumishumi.facade.request.transaction.TransactionCreateRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionPaymentRequest;
import id.thesis.shumishumi.facade.result.transaction.TransactionCreateResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionPaymentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController extends BaseController {

    @Autowired
    private TransactionFacade transactionFacade;

    @PostMapping("/create")
    public ResponseEntity<TransactionCreateResult> create(@RequestHeader HttpHeaders headers, @RequestBody TransactionCreateForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new
                ControllerCallback<TransactionCreateResult, TransactionCreateRequest>() {
                    @Override
                    public void authCheck(String clientId, String clientSecret) {
                        authenticate(clientId, clientSecret);
                    }

                    @Override
                    public TransactionCreateRequest composeRequest() {
                        TransactionCreateRequest request = new TransactionCreateRequest();
                        request.setItems(form.getItems());

                        return request;
                    }

                    @Override
                    public TransactionCreateResult doProcess(TransactionCreateRequest request) {
                        return transactionFacade.create(request);
                    }
                });
    }

    @PostMapping("/payment")
    public ResponseEntity<TransactionPaymentResult> payment(@RequestHeader HttpHeaders headers, @RequestBody TransactionPaymentForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new
                ControllerCallback<TransactionPaymentResult, TransactionPaymentRequest>() {
                    @Override
                    public void authCheck(String clientId, String clientSecret) {
                        authenticate(clientId, clientSecret);
                    }

                    @Override
                    public TransactionPaymentRequest composeRequest() {
                        TransactionPaymentRequest request = new TransactionPaymentRequest();
                        request.setTransactionId(form.getTransactionId());
                        request.setPaymentType(form.getPaymentType());

                        return request;
                    }

                    @Override
                    public TransactionPaymentResult doProcess(TransactionPaymentRequest request) {
                        return transactionFacade.payment(request);
                    }
                });
    }
}
