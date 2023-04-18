package id.thesis.shumishumi.facade.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemWishlistVO extends BaseVO {
    private String userId;
    private String itemId;
}
