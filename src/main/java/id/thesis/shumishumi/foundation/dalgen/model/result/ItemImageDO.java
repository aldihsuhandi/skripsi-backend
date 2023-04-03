package id.thesis.shumishumi.foundation.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;


@Getter
@Setter
@ToString
public class ItemImageDO extends BaseDO {
    private String itemImageId;
    private Blob itemImage;
    private String itemId;
    private boolean isDeleted;
}
