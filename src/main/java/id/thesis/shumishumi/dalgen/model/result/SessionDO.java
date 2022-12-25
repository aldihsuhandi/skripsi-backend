package id.thesis.shumishumi.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class SessionDO implements Serializable {
    private static final long serialVersionUID = -8153517535102798591L;

    private String sessionId;
    private String userId;
    private Date sessionDt;
    private boolean isRemembered;
    private boolean isActive;
    private Date gmtCreate;
    private Date gmtModified;
}
