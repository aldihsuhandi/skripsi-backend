package id.thesis.shumishumi.process.service;

import id.thesis.shumishumi.common.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.core.facade.OTPFacade;
import id.thesis.shumishumi.process.callback.ProcessCallback;
import id.thesis.shumishumi.process.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.rest.request.otp.OTPSendRequest;
import id.thesis.shumishumi.rest.request.otp.OTPValidateRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import id.thesis.shumishumi.rest.result.otp.OTPSendResult;
import id.thesis.shumishumi.rest.result.otp.OTPValidateResult;
import org.springframework.stereotype.Service;

@Service
public class OTPFacadeImpl extends ProcessFacade implements OTPFacade {
    @Override
    public OTPSendResult sendOtp(OTPSendRequest request) {
        return (OTPSendResult) ProcessCallbackSupport.process(ProcessTypeEnum.OTP_SEND, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new OTPSendResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public OTPValidateResult validateOtp(OTPValidateRequest request) {
        return (OTPValidateResult) ProcessCallbackSupport.process(ProcessTypeEnum.OTP_VALIDATE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new OTPValidateResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }
}
