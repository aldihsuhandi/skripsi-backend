package id.thesis.shumishumi.facade.result.transaction;

import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionPaymentResult extends BaseResult {
    private static final long serialVersionUID = 3802538376362373010L;

    private String paymentNumber;
}
