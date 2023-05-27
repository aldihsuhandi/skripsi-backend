package id.thesis.shumishumi.foundation.converter;

import id.thesis.shumishumi.facade.model.viewobject.TransactionDetailVO;
import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;
import id.thesis.shumishumi.foundation.model.result.TransactionDO;
import id.thesis.shumishumi.foundation.model.result.TransactionDetailDO;

public class TransactionDAORequestConverter {
    public static TransactionDO convertTransactionDAORequest(TransactionVO transactionVO) {
        if (transactionVO == null) {
            return null;
        }

        TransactionDO transactionDO = new TransactionDO();
        transactionDO.setTransactionId(transactionVO.getTransactionId());
        transactionDO.setUserId(transactionVO.getUserId());
        transactionDO.setPaymentType(transactionVO.getPaymentType());
        transactionDO.setMidtransId(transactionVO.getMidtransId());
        transactionDO.setMidtransLink(transactionVO.getMidtransLink());
        transactionDO.setStatus(transactionVO.getStatus());

        return transactionDO;
    }

    public static TransactionDetailDO convertTransactionDetailDAORequest(TransactionDetailVO detailVO,
                                                                         String transactionId) {
        if (detailVO == null) {
            return null;
        }

        TransactionDetailDO detailDO = new TransactionDetailDO();
        detailDO.setTransactionId(transactionId);
        detailDO.setTransactionDetailId(detailVO.getTransactionDetailId());
        detailDO.setHistoryItemId(detailVO.getHistoryItemVO().getHistoryItemId());
        detailDO.setQuantity(detailVO.getQuantity());

        return detailDO;
    }
}
