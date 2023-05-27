package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.MidtransService;
import id.thesis.shumishumi.common.service.TransactionService;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.converter.ViewObjectConverter;
import id.thesis.shumishumi.facade.model.enumeration.TransactionStatusEnum;
import id.thesis.shumishumi.facade.model.viewobject.HistoryItemVO;
import id.thesis.shumishumi.facade.model.viewobject.TransactionDetailVO;
import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;
import id.thesis.shumishumi.foundation.converter.TransactionDAORequestConverter;
import id.thesis.shumishumi.foundation.model.result.TransactionDO;
import id.thesis.shumishumi.foundation.service.TransactionDAO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private MidtransService midtransService;

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private ItemService itemService;

    @Override
    public String create(TransactionVO transactionVO) {
        transactionVO.setTransactionId(FunctionUtil.generateUUID());
        transactionDAO.createTransaction(TransactionDAORequestConverter.
                convertTransactionDAORequest(transactionVO));
        transactionVO.getDetails().forEach(detail -> {
            detail.setTransactionDetailId(FunctionUtil.generateUUID());
            transactionDAO.createTransactionDetail(TransactionDAORequestConverter.
                    convertTransactionDetailDAORequest(detail, transactionVO.getTransactionId()));
        });

        return transactionVO.getTransactionId();
    }

    @Override
    public TransactionVO query(String transactionId, boolean withDetail) {
        TransactionVO transaction = ViewObjectConverter.toViewObject(transactionDAO.
                queryTransaction(transactionId));
        transaction.setDetails(new ArrayList<>());
        if (withDetail) {
            List<TransactionDetailVO> details = transactionDAO.queryDetailTransaction(transactionId).
                    stream().map(detail -> {
                        HistoryItemVO history = itemService.queryItemHistory(detail.getHistoryItemId());

                        return ViewObjectConverter.toViewObject(detail, history);
                    }).collect(Collectors.toList());

            transaction.setDetails(details);
        }

        return transaction;
    }

    @Override
    public void update(TransactionVO transaction) {
        TransactionDO transactionDO = new TransactionDO();
        transactionDO.setTransactionId(transaction.getTransactionId());
        transactionDO.setUserId(transaction.getUserId());
        transactionDO.setPaymentType(transaction.getPaymentType());
        transactionDO.setMidtransId(transaction.getMidtransId());
        transactionDO.setMidtransLink(transaction.getMidtransLink());
        transactionDO.setStatus(transaction.getStatus());

        transactionDAO.update(transactionDO);
    }

    @Override
    public void checkPaymentStatus(String transactionId) {
        String status = midtransService.checkStatus(transactionId);
        if (StringUtils.equals(status, "pending")) {
            return;
        }

        if (StringUtils.equals(status, "settlement")) {
            this.changeStatus(transactionId, TransactionStatusEnum.ONGOING.getCode());
            return;
        }

        this.changeStatus(transactionId, TransactionStatusEnum.CANCELED.getCode());
    }

    @Override
    public void changeStatus(String transactionId, String transactionStatus) {
        transactionDAO.updateStatus(transactionId, transactionStatus);
    }
}
