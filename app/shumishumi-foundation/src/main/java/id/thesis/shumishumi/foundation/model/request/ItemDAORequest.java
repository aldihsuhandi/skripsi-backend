package id.thesis.shumishumi.foundation.model.request;

import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.context.SortingContext;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class ItemDAORequest implements Serializable {
    private static final long serialVersionUID = -7254510956609204539L;

    private String itemId;
    private String itemName;
    private Long itemPrice;
    private Long minPrice;
    private Long maxPrice;
    private String itemDescription;
    private Integer itemQuantity;
    private String itemImages;
    private String categoryId;
    private String hobbyId;
    private String merchantId;
    private String merchantLevelId;
    private String userLevelId;
    private boolean isDeleted;
    private boolean isApproved;
    private Date gmtCreate;
    private Date gmtModified;

    private PagingContext pagingContext;
    private SortingContext sortingContext;
}
