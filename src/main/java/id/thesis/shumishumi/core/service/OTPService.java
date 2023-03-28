package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.viewobject.OtpVO;

public interface OTPService {
    void send(String email, String type, String otp);

    OtpVO query(String email, String type, String otp);

    void deactivate(String otpId);

    void deactivate();
}
