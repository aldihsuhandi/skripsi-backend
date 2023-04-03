package id.thesis.shumishumi.foundation.dalgen.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class OTPDAORequest implements Serializable {
    private static final long serialVersionUID = -5400360910196195400L;

    private String otpId;
    private String otp;
    private Date otpDt;
    private String typeId;
    private String email;
    private boolean isActive;
}
