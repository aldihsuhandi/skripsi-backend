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
    private Long price;
}
