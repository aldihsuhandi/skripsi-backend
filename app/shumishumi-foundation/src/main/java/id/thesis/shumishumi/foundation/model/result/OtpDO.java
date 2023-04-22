package id.thesis.shumishumi.foundation.model.result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "otps")
public class OtpDO extends BaseDO {
    private static final long serialVersionUID = 5009021265350229607L;

    @Id
    @Column(name = "otp_id")
    private String otpId;

    @Column(name = "otp")
    private String otp;

    @Column(name = "otp_dt")
    private Date otpDt;

    @Column(name = "type_id")
    private String typeId;

    @Column(name = "email")
    private String email;

    @Column(name = "is_active")
    private boolean isActive;

    public OtpDO() {
    }

    public String getOtpId() {
        return otpId;
    }

    public void setOtpId(String otpId) {
        this.otpId = otpId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Date getOtpDt() {
        return otpDt;
    }

    public void setOtpDt(Date otpDt) {
        this.otpDt = otpDt;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
