package id.thesis.shumishumi.core.processor.transaction;

import id.thesis.shumishumi.common.service.ReviewService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.TransactionService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.enumeration.TransactionStatusEnum;
import id.thesis.shumishumi.facade.model.viewobject.HistoryItemVO;
import id.thesis.shumishumi.facade.model.viewobject.ReviewVO;
import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionFinishRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionFinishProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ReviewService reviewService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        TransactionFinishRequest request = (TransactionFinishRequest) baseRequest;

        String userId = sessionService.query(request.getSessionId()).getUserId();
        TransactionVO transaction = transactionService.query(request.getTransactionId(), true);
        AssertUtil.isNotNull(transaction, "transaction not found", ShumishumiErrorCodeEnum.TRANSACTION_NOT_FOUND);
        AssertUtil.isExpected(transaction.getStatus(), TransactionStatusEnum.ONGOING.getCode(),
                "transaction status is not correct", ShumishumiErrorCodeEnum.TRANSACTION_STATUS_INVALID);
        AssertUtil.isExpected(transaction.getUserId(), userId,
                "this transaction doesn't belong to the current user", ShumishumiErrorCodeEnum.USER_INVALID);

        transaction.getDetails().forEach(detail -> {
            HistoryItemVO itemVO = detail.getHistoryItemVO();
            String itemId = itemVO.getItem().getItemId();
            String merchantId = itemVO.getItem().getMerchantInfo().getUserId();

            ReviewVO reviewVO = new ReviewVO();
            reviewVO.setItemId(itemId);
            reviewVO.setUserId(userId);
            reviewVO.setMerchantId(merchantId);
            reviewVO.setStar(-1);
            reviewVO.setNeedReview(true);

            reviewService.create(reviewVO);
        });

        transactionService.changeStatus(transaction.getTransactionId(), TransactionStatusEnum.DONE.getCode());
    }
}
