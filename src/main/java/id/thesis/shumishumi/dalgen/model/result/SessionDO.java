package id.thesis.shumishumi.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SessionDO extends BaseDO {
    private String sessionId;
    private String userId;
    private Date sessionDt;
    private boolean isRemembered;
    private boolean isActive;
}
