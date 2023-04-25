package id.thesis.shumishumi.foundation.model.result;

import id.thesis.shumishumi.foundation.model.result.primarykey.ItemCrowdDOPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "item_crowds")
public class ItemCrowdDO extends BaseDO {
    private static final long serialVersionUID = 3832624843568011286L;

    @EmbeddedId
    private ItemCrowdDOPK pk;

    public ItemCrowdDOPK getPk() {
        return pk;
    }

    public void setPk(ItemCrowdDOPK pk) {
        this.pk = pk;
    }
}
