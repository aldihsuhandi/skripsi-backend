package id.thesis.shumishumi.foundation.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class ResetPasswordDAORequest implements Serializable {
    private static final long serialVersionUID = 2858563142737974391L;

    private String uuid;
    private String email;
    private Date expiredTime;
    private boolean isActive;
}
