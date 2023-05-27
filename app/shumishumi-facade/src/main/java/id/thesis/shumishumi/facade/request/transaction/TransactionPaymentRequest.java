package id.thesis.shumishumi.facade.request.transaction;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionPaymentRequest extends BaseRequest {
    private static final long serialVersionUID = -1352531987830768478L;

    private String paymentType;
    private String transactionId;
}
