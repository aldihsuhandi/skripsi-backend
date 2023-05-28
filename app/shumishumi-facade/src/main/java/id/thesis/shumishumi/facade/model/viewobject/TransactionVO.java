package id.thesis.shumishumi.facade.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TransactionVO extends BaseVO {
    private static final long serialVersionUID = -6343874297640589116L;

    private String transactionId;
    private List<TransactionDetailVO> details;
    private String userId;
    private String midtransId;
    private String midtransLink;
    private Long price;
    private String status;
    private String paymentType;
}
