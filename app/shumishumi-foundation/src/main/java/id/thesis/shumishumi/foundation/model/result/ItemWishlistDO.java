package id.thesis.shumishumi.foundation.model.result;

import id.thesis.shumishumi.foundation.model.result.primarykey.ItemWishlistDOPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wishlists")
public class ItemWishlistDO extends BaseDO {
    private static final long serialVersionUID = -3542855325121768738L;

    @EmbeddedId
    private ItemWishlistDOPK pk;

    public ItemWishlistDOPK getPk() {
        return pk;
    }

    public void setPk(ItemWishlistDOPK pk) {
        this.pk = pk;
    }
}
