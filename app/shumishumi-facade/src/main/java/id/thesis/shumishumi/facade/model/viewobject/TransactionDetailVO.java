package id.thesis.shumishumi.facade.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionDetailVO extends BaseVO {
    private static final long serialVersionUID = -5016518512830055557L;

    private ItemVO item;
    private int quantity;
}
