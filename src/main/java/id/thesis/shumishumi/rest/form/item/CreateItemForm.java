package id.thesis.shumishumi.rest.form.item;

import id.thesis.shumishumi.rest.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateItemForm extends BaseForm {
    private static final long serialVersionUID = -3724225432015750033L;

    private String itemName;
    private Long itemPrice;
    private String itemDescription;
    private Integer itemQuantity;
    private String categoryName;
    private String hobbyName;
    private String merchantInterestLevel;
}
