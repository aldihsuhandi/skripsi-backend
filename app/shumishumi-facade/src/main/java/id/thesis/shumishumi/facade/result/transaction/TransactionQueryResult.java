package id.thesis.shumishumi.facade.result.transaction;

import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.summary.TransactionSummary;
import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TransactionQueryResult extends BaseResult {
    private static final long serialVersionUID = -2364822305092091009L;

    private List<TransactionSummary> transactions;
    private PagingContext pagingContext;
}
