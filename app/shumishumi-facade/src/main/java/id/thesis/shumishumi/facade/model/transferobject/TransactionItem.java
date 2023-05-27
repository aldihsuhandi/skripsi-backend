package id.thesis.shumishumi.facade.model.transferobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class TransactionItem implements Serializable {
    private static final long serialVersionUID = 3529311199575244547L;

    private String itemId;
    private int quantity = 1;
}
