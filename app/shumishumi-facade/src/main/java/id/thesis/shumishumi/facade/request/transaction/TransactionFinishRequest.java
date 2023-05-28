package id.thesis.shumishumi.facade.request.transaction;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionFinishRequest extends BaseRequest {
    private static final long serialVersionUID = 6790550124703089477L;

    private String transactionId;
}
