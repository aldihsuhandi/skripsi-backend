package id.thesis.shumishumi.core.processor.transaction;

import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.MidtransService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.TransactionService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.context.ItemUpdateContext;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.enumeration.TransactionStatusEnum;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionCancelRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TransactionCancelProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private MidtransService midtransService;

    @Autowired
    private TransactionService transactionService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        TransactionCancelRequest request = (TransactionCancelRequest) baseRequest;

        String userId = sessionService.query(request.getSessionId()).getUserId();
        TransactionVO transaction = transactionService.query(request.getTransactionId(), true);
        AssertUtil.isNotNull(transaction, "transaction not found", ShumishumiErrorCodeEnum.TRANSACTION_NOT_FOUND);
        AssertUtil.isExpected(transaction.getStatus(), TransactionStatusEnum.WAITING_PAYMENT.getCode(),
                "transaction status is not correct", ShumishumiErrorCodeEnum.TRANSACTION_STATUS_INVALID);
        AssertUtil.isExpected(transaction.getUserId(), userId,
                "this transaction doesn't belong to the current user", ShumishumiErrorCodeEnum.USER_INVALID);

        midtransService.cancelPayment(transaction.getMidtransId());
        transactionService.changeStatus(transaction.getTransactionId(), TransactionStatusEnum.CANCELED.getCode());

        List<String> itemIds = new ArrayList<>();
        transaction.getDetails().forEach(detail -> {
            String itemId = detail.getHistoryItemVO().getItem().getItemId();
            ItemVO item = itemService.queryById(itemId, true);

            ItemUpdateContext updateContext = new ItemUpdateContext();
            updateContext.setItemQuantity(item.getItemQuantity() + detail.getQuantity());

            itemService.update(item, updateContext, item.getItemImages());
            itemIds.add(itemId);
        });

        itemService.refreshCache(itemIds, false);
    }
}
