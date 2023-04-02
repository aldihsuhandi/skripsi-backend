package id.thesis.shumishumi.foundation.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;
import java.util.List;

@Getter
@Setter
@ToString
public class ItemDO extends BaseDO {
    private static final long serialVersionUID = 3935724888170635848L;

    private String itemId;
    private String itemName;
    private Long itemPrice;
    private String itemDescription;
    private Integer itemQuantity;
    private String categoryId;
    private String hobbyId;
    private String merchantId;
    private String merchantLevelId;
    private boolean isDeleted;
    private boolean isApproved;
    private List<Blob> itemImages;
}
