package id.thesis.shumishumi.foundation.model.result.primarykey;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ItemWishlistDOPK implements Serializable {
    private static final long serialVersionUID = 3229436141578338249L;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "user_id")
    private String userId;

    public ItemWishlistDOPK(String itemId, String userId) {
        this.itemId = itemId;
        this.userId = userId;
    }

    public ItemWishlistDOPK() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
