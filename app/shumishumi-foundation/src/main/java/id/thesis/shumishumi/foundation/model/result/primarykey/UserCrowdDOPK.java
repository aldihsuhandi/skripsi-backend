package id.thesis.shumishumi.foundation.model.result.primarykey;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserCrowdDOPK implements Serializable {
    private static final long serialVersionUID = -8817980444281430183L;

    @Column(name = "user_id")
    private String userId;
    @Column(name = "crowd_id")
    private String crowdId;

    public UserCrowdDOPK(String userId, String crowdId) {
        this.userId = userId;
        this.crowdId = crowdId;
    }

    public UserCrowdDOPK() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
