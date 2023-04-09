package id.thesis.shumishumi.common.model.context;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemUpdateContext {
    private String itemName;
    private Long itemPrice;
    private String itemDescription;
    private Integer itemQuantity;
    private String categoryName;
    private String hobbyName;
    private String merchantInterestLevel;
}
