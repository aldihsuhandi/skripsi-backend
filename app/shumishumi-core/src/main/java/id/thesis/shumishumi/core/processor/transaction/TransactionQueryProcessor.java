package id.thesis.shumishumi.core.processor.transaction;

import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.TransactionService;
import id.thesis.shumishumi.core.converter.SummaryConverter;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.enumeration.TransactionStatusEnum;
import id.thesis.shumishumi.facade.model.summary.TransactionSummary;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.transaction.TransactionQueryRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.transaction.TransactionQueryResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionQueryProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private TransactionService transactionService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        TransactionQueryRequest request = (TransactionQueryRequest) baseRequest;
        TransactionQueryResult result = (TransactionQueryResult) baseResult;

        String userId = sessionService.query(request.getSessionId()).getUserId();
        TransactionStatusEnum statusEnum = TransactionStatusEnum.findByName(request.getStatus());
        String status = "";
        if (statusEnum != null) {
            status = statusEnum.getCode();
        }

        PagingContext pagingContext = new PagingContext();
        pagingContext.setPageNumber(request.getPageNumber());
        pagingContext.setNumberOfItem(request.getNumberOfItem());

        List<TransactionSummary> transactions = transactionService.queryList(userId, status, pagingContext).
                stream().map(SummaryConverter::toSummary).collect(Collectors.toList());

        pagingContext.calculateTotalPage();
        pagingContext.checkHasNext(pagingContext.getTotalItem(), pagingContext.getNumberOfItem());

        result.setTransactions(transactions);
        result.setPagingContext(pagingContext);
    }
}
