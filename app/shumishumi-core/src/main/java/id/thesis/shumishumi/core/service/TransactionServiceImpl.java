package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.service.TransactionService;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;
import id.thesis.shumishumi.foundation.converter.TransactionDAORequestConverter;
import id.thesis.shumishumi.foundation.service.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDAO transactionDAO;

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
}
