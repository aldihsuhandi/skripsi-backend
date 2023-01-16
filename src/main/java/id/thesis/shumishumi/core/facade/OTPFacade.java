package id.thesis.shumishumi.core.facade;

import id.thesis.shumishumi.rest.request.otp.OTPSendRequest;
import id.thesis.shumishumi.rest.request.otp.OTPValidateRequest;
import id.thesis.shumishumi.rest.result.otp.OTPSendResult;
import id.thesis.shumishumi.rest.result.otp.OTPValidateResult;

public interface OTPFacade {
    OTPSendResult sendOtp(OTPSendRequest request);

    OTPValidateResult validateOtp(OTPValidateRequest request);
}
