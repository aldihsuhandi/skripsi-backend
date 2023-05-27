package id.thesis.shumishumi.facade.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ItemVO extends BaseVO {
    private static final long serialVersionUID = 3006395474977580386L;

    private String itemId;
    private String itemName;
    private Long itemPrice;
    private String itemDescription;
    private Integer itemQuantity;
    private List<String> itemImages;
    private ItemCategoryVO itemCategory;
    private HobbyVO hobby;
    private UserVO merchantInfo;
    private InterestLevelVO merchantLevel;
    private InterestLevelVO userLevel;
    private boolean isDeleted;
    private boolean isApproved;
}
