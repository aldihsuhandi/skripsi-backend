/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.rest.request.item;

import id.thesis.shumishumi.rest.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;
import java.util.List;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: CreateItemRequest.java, v 0.1 2023‐01‐16 4:22 PM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class CreateItemRequest extends BaseRequest {
    private static final long serialVersionUID = -3836436949436330238L;

    private String itemName;
    private Long itemPrice;
    private String itemDescription;
    private Integer itemQuantity;
    private String categoryName;
    private String hobbyName;
    private String merchantInterestLevel;
    private List<Blob> itemImages;
}
