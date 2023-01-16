package id.thesis.shumishumi.dalgen.model.request;

import id.thesis.shumishumi.common.model.context.PagingContext;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class ItemDAORequest implements Serializable {
    private static final long serialVersionUID = -7254510956609204539L;

    private String itemId;
    private String itemName;
    private Long itemPrice;
    private String itemDescription;
    private Integer itemQuantity;
    private String categoryName;
    private String hobbyName;
    private String merchantInterestLeve;
    private List<Blob> itemImages;
    private Date gmtCreate;
    private Date gmtModified;

    private PagingContext pagingContext;
}
