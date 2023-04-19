package id.thesis.shumishumi.facade.model.context;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> addedImage = new ArrayList<>();
    private List<String> removedImage = new ArrayList<>();
}
