package id.thesis.shumishumi.facade.result.transaction;

import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionCreateResult extends BaseResult {
    private static final long serialVersionUID = 2699397566798309651L;

    private String transactionId;
}
