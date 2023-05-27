package id.thesis.shumishumi.facade.model.summary;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TransactionSummary extends BaseSummary {
    private static final long serialVersionUID = -7703721396003107462L;

    private String transactionId;
    private List<TransactionDetailSummary> details;
    private Long price;
    private String status;
    private String paymentType;
    private String paymentCode;
}
