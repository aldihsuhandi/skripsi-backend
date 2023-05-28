package id.thesis.shumishumi.facade.request.transaction;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionCancelRequest extends BaseRequest {
    private static final long serialVersionUID = -9065418476735241264L;

    private String transactionId;
}
