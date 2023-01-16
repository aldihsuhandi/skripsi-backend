package id.thesis.shumishumi.common.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class OtpVO extends BaseVO {
    private static final long serialVersionUID = -2523807445161024681L;


    private String otpId;
    private String otp;
    private Date otpDt;
    private String otpType;
    private String email;
    private boolean isActive;
}
