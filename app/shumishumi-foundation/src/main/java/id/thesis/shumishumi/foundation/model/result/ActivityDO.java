package id.thesis.shumishumi.foundation.model.result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activities")
public class ActivityDO extends BaseDO {
    private static final long serialVersionUID = -447519860279938265L;

    @Id
    @Column(name = "activity_id")
    private String activityId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "activity_value")
    private int activityValue;

    @Column(name = "activity")
    private String activity;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getActivityValue() {
        return activityValue;
    }

    public void setActivityValue(int activityValue) {
        this.activityValue = activityValue;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
