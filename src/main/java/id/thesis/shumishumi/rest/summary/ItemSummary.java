package id.thesis.shumishumi.rest.summary;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;
import java.util.List;

@Getter
@Setter
@ToString
public class ItemSummary extends BaseSummary {
    private static final long serialVersionUID = -7066671259051214790L;

    private String itemId;
    private String itemName;
    private Long itemPrice;
    private String itemDescription;
    private Integer itemQuantity;
    private String itemCategory;
    private String hobby;
    private UserSummary merchantInfo;
    private String merchantLevel;
    private List<Blob> itemImages;
}
