package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.facade.api.TransactionFacade;
import id.thesis.shumishumi.facade.request.transaction.TransactionCancelRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionCreateRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionFinishRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionPaymentRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionQueryRequest;
import id.thesis.shumishumi.facade.result.transaction.TransactionCancelResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionCreateResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionFinishResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionPaymentResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionQueryResult;
import id.thesis.shumishumi.foundation.model.result.CartDO;
import id.thesis.shumishumi.foundation.model.result.TransactionDO;
import id.thesis.shumishumi.foundation.model.result.primarykey.CartDOPK;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TransactionFacadeTest extends FacadeTestBase {

    @Autowired
    private TransactionFacade transactionFacade;

    @BeforeEach
    public void setup() {
        mockitoSession("user");
    }

    @Test
    public void createTransactionTest_SUCCESS() {
        TransactionCreateRequest request = new TransactionCreateRequest();
        request.setFromCart(true);

        Mockito.when(cartDAO.queryAllSelected(Mockito.any())).thenReturn(mockCarts());
        mockItemWithInfo();

        TransactionCreateResult result = transactionFacade.create(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void payTransactionTest_SUCCESS() {
        TransactionPaymentRequest request = new TransactionPaymentRequest();
        request.setTransactionId("trxId");
        request.setPaymentType("BCA Virtual Account");

        JSONObject midtransResult = new JSONObject();
        midtransResult.put("status_code", "201");
        midtransResult.put("transaction_id", "paymentId");
        midtransResult.put("payment_type", "BCA_VA");
        midtransResult.put("transaction_status", "waiting");

        JSONArray vaNums = new JSONArray();
        JSONObject vaNum = new JSONObject();
        vaNum.put("va_number", "123456");
        vaNums.put(vaNum);

        midtransResult.put("va_numbers", vaNums);

        Mockito.when(transactionDAO.queryTransaction(Mockito.any())).thenReturn(mockTransaction("INIT"));
        Mockito.when(midtransClient.createPayment(Mockito.any())).thenReturn(midtransResult);

        TransactionPaymentResult result = transactionFacade.payment(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void finishTransactionTest_SUCCESS() {
        TransactionFinishRequest request = new TransactionFinishRequest();
        request.setTransactionId("trxId");

        Mockito.when(transactionDAO.queryTransaction(Mockito.any())).thenReturn(mockTransaction("ONGOING"));

        TransactionFinishResult result = transactionFacade.finish(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void cancelTransactionTest_SUCCESS() {
        TransactionCancelRequest request = new TransactionCancelRequest();
        request.setTransactionId("trxId");

        JSONObject midtransResult = new JSONObject();
        midtransResult.put("transaction_status", "cancel");
        midtransResult.put("status_code", "200");

        Mockito.when(transactionDAO.queryTransaction(Mockito.any())).thenReturn(mockTransaction("WAITING_PAYMENT"));
        Mockito.when(midtransClient.cancelPayment(Mockito.any())).thenReturn(midtransResult);

        TransactionCancelResult result = transactionFacade.cancel(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void queryTransactionTest_SUCCESS() {
        TransactionQueryRequest request = new TransactionQueryRequest();

        List<TransactionDO> transactions = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            transactions.add(mockTransaction("DONE"));
        }

        Mockito.when(transactionDAO.queryList(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(transactions);

        TransactionQueryResult result = transactionFacade.query(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    private List<CartDO> mockCarts() {
        List<CartDO> carts = new ArrayList<>();
        for (int i = 0; i < 2; ++i) {
            CartDO cart = new CartDO();
            cart.setSelected(true);
            cart.setQuantity(3);
            cart.setPk(new CartDOPK("userId", "itemId"));

            carts.add(cart);
        }

        return carts;
    }

    private TransactionDO mockTransaction(String status) {
        TransactionDO trx = new TransactionDO();
        trx.setPrice(1000L);
        trx.setTransactionId("trxId");
        trx.setUserId("user");
        trx.setStatus(status);

        return trx;
    }
}
