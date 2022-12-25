package id.thesis.shumishumi.common.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SessionVO extends BaseVO {
    private String sessionId;
    private String userId;
    private Date sessionDt;
    private boolean isRemembered;
    private boolean isActive;
    private Date gmtCreate;
    private Date gmtModified;
}
