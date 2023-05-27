package id.thesis.shumishumi.core.processor.transaction;

import id.thesis.shumishumi.common.model.result.midtrans.MidtransChargeInnerResult;
import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.MidtransService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.TransactionService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.context.ItemUpdateContext;
import id.thesis.shumishumi.facade.model.enumeration.PaymentEnum;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.enumeration.TransactionStatusEnum;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.model.viewobject.TransactionDetailVO;
import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionPaymentRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionPaymentResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TransactionPaymentProcessor implements BaseProcessor {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MidtransService midtransService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private SessionService sessionService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        TransactionPaymentRequest request = (TransactionPaymentRequest) baseRequest;
        TransactionPaymentResult result = (TransactionPaymentResult) baseResult;

        PaymentEnum payment = PaymentEnum.findByName(request.getPaymentType());
        AssertUtil.isNotNull(payment, "payment type is not supported", ShumishumiErrorCodeEnum.SYSTEM_ERROR);

        String userId = sessionService.query(request.getSessionId()).getUserId();
        long price = 0;

        TransactionVO transaction = transactionService.query(request.getTransactionId(), true);
        AssertUtil.isNotNull(transaction, "transaction not found", ShumishumiErrorCodeEnum.TRANSACTION_NOT_FOUND);
        AssertUtil.isExpected(transaction.getUserId(), userId, "this transaction is not from this user", ShumishumiErrorCodeEnum.USER_INVALID);

        for (TransactionDetailVO detail : transaction.getDetails()) {
            ItemVO itemVO = itemService.queryById(detail.getHistoryItemVO().getItem().getItemId(), true);
            AssertUtil.isNotNull(itemVO, "item is not found", ShumishumiErrorCodeEnum.ITEM_NOT_FOUND);
            AssertUtil.isExpected(itemVO.getItemQuantity() >= detail.getQuantity(),
                    "item quantity not enough", ShumishumiErrorCodeEnum.ITEM_NOT_ENOUGH);

            price = detail.getQuantity() * detail.getHistoryItemVO().getItem().getItemPrice();
        }

        transaction.setPrice(price);
        transaction.setPaymentType(payment.getName());

        MidtransChargeInnerResult midtransResult = midtransService.createPayment(transaction);

        transaction.setMidtransId(midtransResult.getMidtransId());
        transaction.setMidtransLink(midtransResult.getMidtransLink());
        transaction.setStatus(TransactionStatusEnum.WAITING_PAYMENT.getCode());

        List<String> itemIds = new ArrayList<>();
        transaction.getDetails().forEach(detail -> {
            ItemVO itemVO = itemService.queryById(detail.getHistoryItemVO().getItem().getItemId(), true);
            ItemUpdateContext updateContext = new ItemUpdateContext();
            updateContext.setItemQuantity(itemVO.getItemQuantity() - detail.getQuantity());

            itemService.update(itemVO, updateContext);

            itemIds.add(itemVO.getItemId());
        });

        itemService.refreshCache(itemIds, false);

        transactionService.update(transaction);
        result.setPaymentNumber(midtransResult.getMidtransLink());
    }
}
