package id.thesis.shumishumi.facade.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;

@Getter
@Setter
@ToString
public class ItemImageVO extends BaseVO {
    private String itemId;
    private String itemImageId;
    private Blob itemImage;
    private boolean isDeleted;
}
