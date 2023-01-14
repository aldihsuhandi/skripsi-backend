package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.request.otp.OTPSendInnerRequest;

public interface EmailService {
    public void sendOtpEmail(OTPSendInnerRequest request);
}
