package id.thesis.shumishumi.foundation.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class ItemWishlistDO implements Serializable {
    private static final long serialVersionUID = -3542855325121768738L;

    private String itemId;
    private String userId;
    private Date gmtCreate;
    private Date gmtModified;
}
