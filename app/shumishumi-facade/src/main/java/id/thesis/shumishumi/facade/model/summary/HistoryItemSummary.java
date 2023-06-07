package id.thesis.shumishumi.facade.model.summary;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class HistoryItemSummary extends BaseSummary {
    private static final long serialVersionUID = 1476446950814299397L;

    private String itemId;
    private String itemName;
    private Long itemPrice;
    private String itemDescription;
    private String itemCategory;
    private String hobby;
    private UserSummary merchantInfo;
    private String merchantLevel;
    private String userLevel;
    private List<String> itemImages;
}
