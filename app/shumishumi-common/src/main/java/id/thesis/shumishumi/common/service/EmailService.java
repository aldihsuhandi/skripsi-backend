package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.common.model.request.otp.OTPSendInnerRequest;

public interface EmailService {
    void sendOtpEmail(OTPSendInnerRequest request);

    void sendForgotPasswordEmail(String email, String url);
}
