package id.thesis.shumishumi.core.facade;

import id.thesis.shumishumi.rest.request.otp.OTPSendRequest;
import id.thesis.shumishumi.rest.result.otp.OTPSendResult;

public interface OTPFacade {
    OTPSendResult sendOtp(OTPSendRequest request);
}
