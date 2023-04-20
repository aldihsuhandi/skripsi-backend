package id.thesis.shumishumi.foundation.model.result;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "sessions")
public class SessionDO extends BaseDO {
    private static final long serialVersionUID = -8592637711350770085L;

    @Id
    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "session_dt")
    private Date sessionDt;

    @Column(name = "is_remembered")
    private boolean isRemembered;

    @Column(name = "is_active")
    private boolean isActive;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getSessionDt() {
        return sessionDt;
    }

    public void setSessionDt(Date sessionDt) {
        this.sessionDt = sessionDt;
    }

    public boolean isRemembered() {
        return isRemembered;
    }

    public void setRemembered(boolean remembered) {
        isRemembered = remembered;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
