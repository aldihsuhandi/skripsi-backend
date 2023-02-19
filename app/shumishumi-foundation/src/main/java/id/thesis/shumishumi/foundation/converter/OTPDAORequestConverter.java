package id.thesis.shumishumi.foundation.converter;

import id.thesis.shumishumi.common.model.enumeration.OTPTypeEnum;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.foundation.model.request.OTPDAORequest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class OTPDAORequestConverter {
    public static OTPDAORequest toDAORequest(String email, String otp, String type) {
        OTPTypeEnum typeEnum = OTPTypeEnum.findByName(type);
        Date otpDt = Date.from(LocalDateTime.now().plus(Duration.
                of(10, ChronoUnit.MINUTES)).atZone(ZoneId.systemDefault()).toInstant());

        OTPDAORequest request = new OTPDAORequest();
        request.setOtpId(FunctionUtil.generateUUID());
        request.setOtp(otp);
        request.setOtpDt(otpDt);
        request.setEmail(email);
        request.setTypeId(typeEnum.getId());

        return request;
    }

    public static OTPDAORequest toDAORequest(String otpId) {
        OTPDAORequest request = new OTPDAORequest();
        request.setOtpId(otpId);

        return request;
    }
}
