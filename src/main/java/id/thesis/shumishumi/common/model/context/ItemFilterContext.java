/**
 * 
 *
 */
package id.thesis.shumishumi.common.model.context;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ItemFilterContext.java, v 0.1 2023‐01‐18 11:41 Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class ItemFilterContext {
    private String itemId;
    private String itemName;
    private Long minItemPrice;
    private Long maxItemPrice;
    private String merchantInterestLevel;
    private String userInterestLevel;
    private String merchantEmail;
    private String merchantId;
    private String hobby;
    private boolean isDeleted = false;
    private boolean isApproved = true;
    private String itemCategory;
}
