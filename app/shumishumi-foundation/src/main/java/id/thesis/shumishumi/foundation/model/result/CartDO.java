package id.thesis.shumishumi.foundation.model.result;

import id.thesis.shumishumi.foundation.model.result.primarykey.CartDOPK;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "carts")
public class CartDO extends BaseDO {
    private static final long serialVersionUID = 5169624673666485452L;

    @EmbeddedId
    private CartDOPK pk;

    @Column(name = "quantity")
    private int quantity;

    public CartDOPK getPk() {
        return pk;
    }

    public void setPk(CartDOPK pk) {
        this.pk = pk;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
