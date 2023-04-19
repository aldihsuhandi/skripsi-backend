package id.thesis.shumishumi.foundation.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class SessionDAORequest implements Serializable {
    private static final long serialVersionUID = 7878450298041492741L;

    private String sessionId;
    private String userId;
    private Date sessionDt;
    private boolean isRemembered;
    private boolean isActive;
    private Date gmtCreate;
    private Date gmtModified;
}
