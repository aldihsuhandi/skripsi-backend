package id.thesis.shumishumi.common.process.service;

import id.thesis.shumishumi.common.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.core.facade.OTPFacade;
import id.thesis.shumishumi.common.process.callback.ProcessCallback;
import id.thesis.shumishumi.common.process.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.core.request.otp.OTPSendRequest;
import id.thesis.shumishumi.core.request.otp.OTPValidateRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.result.otp.OTPSendResult;
import id.thesis.shumishumi.core.result.otp.OTPValidateResult;
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
