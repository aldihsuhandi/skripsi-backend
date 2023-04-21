package id.thesis.shumishumi.foundation.model.result;

import id.thesis.shumishumi.foundation.model.result.primarykey.UserCrowdDOPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_crowds")
public class UserCrowdDO extends BaseDO {
    private static final long serialVersionUID = -5163854961644413267L;

    @EmbeddedId
    private UserCrowdDOPK pk;

    public UserCrowdDOPK getPk() {
        return pk;
    }

    public void setPk(UserCrowdDOPK pk) {
        this.pk = pk;
    }
}
