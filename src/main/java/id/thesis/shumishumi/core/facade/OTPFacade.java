package id.thesis.shumishumi.core.facade;

import id.thesis.shumishumi.core.request.otp.OTPSendRequest;
import id.thesis.shumishumi.core.request.otp.OTPValidateRequest;
import id.thesis.shumishumi.core.result.otp.OTPSendResult;
import id.thesis.shumishumi.core.result.otp.OTPValidateResult;

public interface OTPFacade {
    OTPSendResult sendOtp(OTPSendRequest request);

    OTPValidateResult validateOtp(OTPValidateRequest request);
}
