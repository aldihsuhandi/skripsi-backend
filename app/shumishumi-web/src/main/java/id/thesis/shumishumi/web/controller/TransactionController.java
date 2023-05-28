package id.thesis.shumishumi.web.controller;

import id.thesis.shumishumi.common.model.form.transaction.TransactionCancelForm;
import id.thesis.shumishumi.common.model.form.transaction.TransactionCreateForm;
import id.thesis.shumishumi.common.model.form.transaction.TransactionFinishForm;
import id.thesis.shumishumi.common.model.form.transaction.TransactionPaymentForm;
import id.thesis.shumishumi.common.model.form.transaction.TransactionQueryDetailForm;
import id.thesis.shumishumi.common.model.form.transaction.TransactionQueryForm;
import id.thesis.shumishumi.core.callback.ControllerCallback;
import id.thesis.shumishumi.core.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.facade.api.TransactionFacade;
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

    @PostMapping("/detail")
    public ResponseEntity<TransactionQueryDetailResult> detail(@RequestHeader HttpHeaders headers,
                                                               @RequestBody TransactionQueryDetailForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON,
                new ControllerCallback<TransactionQueryDetailResult, TransactionQueryDetailRequest>() {
                    @Override
                    public void authCheck(String clientId, String clientSecret) {
                        authenticate(clientId, clientSecret);
                    }

                    @Override
                    public TransactionQueryDetailRequest composeRequest() {
                        TransactionQueryDetailRequest request = new TransactionQueryDetailRequest();
                        request.setTransactionId(form.getTransactionId());

                        return request;
                    }

                    @Override
                    public TransactionQueryDetailResult doProcess(TransactionQueryDetailRequest request) {
                        return transactionFacade.queryDetail(request);
                    }
                });
    }

    @PostMapping("/query")
    public ResponseEntity<TransactionQueryResult> query(@RequestHeader HttpHeaders headers,
                                                        @RequestBody TransactionQueryForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON,
                new ControllerCallback<TransactionQueryResult, TransactionQueryRequest>() {
                    @Override
                    public void authCheck(String clientId, String clientSecret) {
                        authenticate(clientId, clientSecret);
                    }

                    @Override
                    public TransactionQueryRequest composeRequest() {
                        TransactionQueryRequest request = new TransactionQueryRequest();
                        request.setStatus(form.getStatus());
                        request.setNumberOfItem(form.getNumberOfItems());
                        request.setPageNumber(form.getPageNumber());

                        return request;
                    }

                    @Override
                    public TransactionQueryResult doProcess(TransactionQueryRequest request) {
                        return transactionFacade.query(request);
                    }
                });
    }

    @PostMapping("/finish")
    public ResponseEntity<TransactionFinishResult> finish(@RequestHeader HttpHeaders headers,
                                                          @RequestBody TransactionFinishForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON,
                new ControllerCallback<TransactionFinishResult, TransactionFinishRequest>() {
                    @Override
                    public void authCheck(String clientId, String clientSecret) {
                        authenticate(clientId, clientSecret);
                    }

                    @Override
                    public TransactionFinishRequest composeRequest() {
                        TransactionFinishRequest request = new TransactionFinishRequest();
                        request.setTransactionId(form.getTransactionId());

                        return request;
                    }

                    @Override
                    public TransactionFinishResult doProcess(TransactionFinishRequest request) {
                        return transactionFacade.finish(request);
                    }
                });
    }

    @PostMapping("/cancel")
    public ResponseEntity<TransactionCancelResult> cancel(@RequestHeader HttpHeaders headers,
                                                          @RequestBody TransactionCancelForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON,
                new ControllerCallback<TransactionCancelResult, TransactionCancelRequest>() {
                    @Override
                    public void authCheck(String clientId, String clientSecret) {
                        authenticate(clientId, clientSecret);
                    }

                    @Override
                    public TransactionCancelRequest composeRequest() {
                        TransactionCancelRequest request = new TransactionCancelRequest();
                        request.setTransactionId(form.getTransactionId());

                        return request;
                    }

                    @Override
                    public TransactionCancelResult doProcess(TransactionCancelRequest request) {
                        return transactionFacade.cancel(request);
                    }
                });
    }
}
