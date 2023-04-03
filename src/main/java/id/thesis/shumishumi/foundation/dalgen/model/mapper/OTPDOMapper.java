package id.thesis.shumishumi.foundation.dalgen.model.mapper;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.foundation.dalgen.model.result.OtpDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OTPDOMapper implements RowMapper<OtpDO> {
    @Override
    public OtpDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        OtpDO otpDO = new OtpDO();
        otpDO.setOtpId(rs.getString(DatabaseConst.OTP_ID));
        otpDO.setOtp(rs.getString(DatabaseConst.OTP));
        otpDO.setOtpDt(rs.getTimestamp(DatabaseConst.OTP_DT));
        otpDO.setTypeId(rs.getString(DatabaseConst.OTP_TYPE_ID));
        otpDO.setActive(rs.getBoolean(DatabaseConst.IS_ACTIVE));
        otpDO.setEmail(rs.getString(DatabaseConst.EMAIL));
        otpDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        otpDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return otpDO;
    }
}
