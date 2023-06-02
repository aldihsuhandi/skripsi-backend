package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.TransactionDO;
import id.thesis.shumishumi.foundation.model.result.TransactionDetailDO;
import id.thesis.shumishumi.foundation.repository.TransactionDetailRepository;
import id.thesis.shumishumi.foundation.repository.TransactionRepository;
import id.thesis.shumishumi.foundation.service.TransactionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionDAOImpl implements TransactionDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Override
    public void createTransaction(TransactionDO transaction) {
        LogUtil.info(LOGGER, String.format("transactionDAO#createTransaction[transaction=%s]", transaction));
        try {
            transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void createTransactionDetail(TransactionDetailDO detailDO) {
        LogUtil.info(LOGGER, String.format("transactionDAO#createTransactionDetail[detail=%s]", detailDO));
        try {
            transactionDetailRepository.save(detailDO);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public TransactionDO queryTransaction(String transactionId) {
        LogUtil.info(LOGGER, String.format("transactionDAO#queryTransaction[transactionId=%s]", transactionId));
        TransactionDO transactionDO;
        try {
            transactionDO = transactionRepository.findById(transactionId).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("transactionDAO#queryTransaction[transaction=%s]", transactionDO));
        return transactionDO;
    }

    @Override
    public List<TransactionDO> queryList(String status) {
        LogUtil.info(LOGGER, String.format("transactionDAO#queryList[status=%s]", status));
        List<TransactionDO> transactions;
        try {
            transactions = transactionRepository.findByStatus(status);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("transactionDAO#queryList[transactions=%s]", transactions));
        return transactions;
    }

    @Override
    public List<TransactionDO> queryList(String userId, String status, PagingContext pagingContext) {
        LogUtil.info(LOGGER, String.format("transactionDAO#queryList[userId=%s,status=%s,pagingContext=%s]", userId, status, pagingContext));
        List<TransactionDO> transactions;
        try {
            Pageable pageable = PageRequest.of(pagingContext.getPageNumber() - 1, pagingContext.getNumberOfItem());
            Page<TransactionDO> pages = transactionRepository.queryByStatus(userId, status, pageable);

            pagingContext.setTotalItem(pages.getTotalElements());
            transactions = pages.getContent();
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("transactionDAO#queryList[transactions=%s]", transactions));
        return transactions;
    }

    @Override
    public List<TransactionDetailDO> queryDetailTransaction(String transactionId) {
        LogUtil.info(LOGGER, String.format("transactionDAO#queryDetailTransaction[transactionId=%s]", transactionId));
        List<TransactionDetailDO> detailDOS;
        try {
            detailDOS = transactionDetailRepository.findByTransactionId(transactionId);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("transactionDAO#queryDetailTransaction[details=%s]", detailDOS.toString()));
        return detailDOS;
    }

    @Override
    public void updateStatus(String transactionId, String transactionStatus) {
        LogUtil.info(LOGGER, String.format("transactionDAO#updateStatus[transactionId=%s,transactionStatus=%s]",
                transactionId, transactionStatus));
        try {
            transactionRepository.updateStatusById(transactionId, transactionStatus);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void update(TransactionDO transaction) {
        LogUtil.info(LOGGER, String.format("transactionDAO#update[transaction=%s]", transaction));
        try {
            transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void delete(String transactionId) {
        LogUtil.info(LOGGER, String.format("transactionDAO#delete[transactionId=%s]", transactionId));
        try {
            transactionRepository.deleteById(transactionId);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }
}
