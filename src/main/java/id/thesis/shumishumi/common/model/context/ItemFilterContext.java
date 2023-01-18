/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.common.model.context;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
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
    private String hobby;
    private String itemCategory;
}
