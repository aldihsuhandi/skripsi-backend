/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.common.model.request.item;

import id.thesis.shumishumi.common.model.request.BaseInnerRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;
import java.util.List;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: CreateItemInnerRequest.java, v 0.1 2023‐01‐16 5:00 PM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class CreateItemInnerRequest extends BaseInnerRequest {
    private static final long serialVersionUID = -4904517867685475927L;

    private String itemId;
    private String itemName;
    private Long itemPrice;
    private String itemDescription;
    private Integer itemQuantity;
    private String categoryName;
    private String hobbyName;
    private String merchantInterestLeve;
    private List<Blob> itemImages;
    private String merchantId;
}
