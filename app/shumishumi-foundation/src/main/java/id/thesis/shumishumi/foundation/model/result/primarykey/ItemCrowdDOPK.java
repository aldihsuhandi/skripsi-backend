package id.thesis.shumishumi.foundation.model.result.primarykey;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ItemCrowdDOPK implements Serializable {
    private static final long serialVersionUID = 1923488621223095235L;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "crowd_id")
    private String crowdId;

    public ItemCrowdDOPK(String itemId, String crowdId) {
        this.itemId = itemId;
        this.crowdId = crowdId;
    }

    public ItemCrowdDOPK() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCrowdId() {
        return crowdId;
    }

    public void setCrowdId(String crowdId) {
        this.crowdId = crowdId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
