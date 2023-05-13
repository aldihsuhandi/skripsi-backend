package id.thesis.shumishumi.foundation.model.result.primarykey;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CartDOPK implements Serializable {
    private static final long serialVersionUID = -7716782286850487882L;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "item_id")
    private String itemId;

    public CartDOPK() {
        ;
    }

    public CartDOPK(String userId, String itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
