package id.thesis.shumishumi.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class OtpDO extends BaseDO {
    private static final long serialVersionUID = 5009021265350229607L;

    private String otpId;
    private String otp;
    private Date otpDt;
    private String typeId;
    private String email;
    private boolean isActive;
}
