package id.thesis.shumishumi.facade.request.transaction;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionQueryDetailRequest extends BaseRequest {
    private static final long serialVersionUID = 9074098013689109021L;

    private String transactionId;
}
