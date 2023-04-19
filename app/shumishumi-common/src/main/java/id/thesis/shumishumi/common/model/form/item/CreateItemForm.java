package id.thesis.shumishumi.common.model.form.item;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CreateItemForm extends BaseForm {
    private static final long serialVersionUID = -3724225432015750033L;

    private String itemName;
    private Long itemPrice;
    private String itemDescription;
    private List<String> itemImages;
    private Integer itemQuantity;
    private String categoryName;
    private String hobbyName;
    private String merchantInterestLevel;
}
