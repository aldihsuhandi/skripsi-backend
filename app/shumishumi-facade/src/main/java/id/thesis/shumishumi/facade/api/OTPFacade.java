package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.otp.OTPSendRequest;
import id.thesis.shumishumi.facade.request.otp.OTPValidateRequest;
import id.thesis.shumishumi.facade.result.otp.OTPSendResult;
import id.thesis.shumishumi.facade.result.otp.OTPValidateResult;

public interface OTPFacade {
    OTPSendResult sendOtp(OTPSendRequest request);

    OTPValidateResult validateOtp(OTPValidateRequest request);
}
