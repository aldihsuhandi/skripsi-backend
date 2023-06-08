package id.thesis.shumishumi.core.processor.transaction;

import id.thesis.shumishumi.common.service.CartService;
import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.TransactionService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.enumeration.TransactionStatusEnum;
import id.thesis.shumishumi.facade.model.transferobject.TransactionItem;
import id.thesis.shumishumi.facade.model.viewobject.HistoryItemVO;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.model.viewobject.TransactionDetailVO;
import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionCreateRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionCreateResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionCreateProcessor implements BaseProcessor {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private CartService cartService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        TransactionCreateRequest request = (TransactionCreateRequest) baseRequest;
        TransactionCreateResult result = (TransactionCreateResult) baseResult;

        SessionVO session = sessionService.query(request.getSessionId());

        List<TransactionItem> transactionItems;
        TransactionVO transactionVO = new TransactionVO();
        List<TransactionDetailVO> detailVOS = new ArrayList<>();
        long price = 0L;

        if (request.isFromCart()) {
            transactionItems = getFromCart(session.getUserId());
        } else {
            transactionItems = request.getItems();
        }

        transactionItems.forEach(item -> {
            ItemVO itemVO = itemService.queryById(item.getItemId(), true);
            AssertUtil.isNotNull(itemVO, "one or more item in your cart cannot be found", ShumishumiErrorCodeEnum.ITEM_NOT_FOUND);
            AssertUtil.isExpected(itemVO.getItemQuantity() >= item.getQuantity(),
                    "one or more item in your cart is not enough", ShumishumiErrorCodeEnum.ITEM_NOT_ENOUGH);

            String historyId = itemService.createHistoryItem(itemVO);
            HistoryItemVO historyItemVO = new HistoryItemVO();
            historyItemVO.setHistoryItemId(historyId);
            historyItemVO.setItem(itemVO);

            TransactionDetailVO detailVO = new TransactionDetailVO();
            detailVO.setQuantity(item.getQuantity());
            detailVO.setHistoryItemVO(historyItemVO);

            detailVOS.add(detailVO);
        });

        for (TransactionDetailVO detail : detailVOS) {
            int quantity = detail.getQuantity();
            Long itemPrice = detail.getHistoryItemVO().getItem().getItemPrice();

            price += quantity * itemPrice;
        }

        transactionVO.setUserId(session.getUserId());
        transactionVO.setDetails(detailVOS);
        transactionVO.setPrice(price);
        transactionVO.setStatus(TransactionStatusEnum.INIT.getCode());

        String transactionId = transactionService.create(transactionVO);

        result.setTransactionId(transactionId);
    }

    private List<TransactionItem> getFromCart(String userId) {
        return cartService.queryCartSelectedList(userId).stream().map(cart -> {
            TransactionItem item = new TransactionItem();
            item.setQuantity(cart.getQuantity());
            item.setItemId(cart.getItem().getItemId());

            return item;
        }).collect(Collectors.toList());
    }
}
