package id.thesis.shumishumi.service.api;

import id.thesis.shumishumi.core.callback.ProcessCallback;
import id.thesis.shumishumi.core.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.facade.api.OTPFacade;
import id.thesis.shumishumi.facade.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.facade.request.otp.OTPSendRequest;
import id.thesis.shumishumi.facade.request.otp.OTPValidateRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.otp.OTPSendResult;
import id.thesis.shumishumi.facade.result.otp.OTPValidateResult;
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
