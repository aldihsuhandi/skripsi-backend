package id.thesis.shumishumi.service.api;

import id.thesis.shumishumi.core.callback.ProcessCallback;
import id.thesis.shumishumi.core.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.facade.api.ForgotPasswordFacade;
import id.thesis.shumishumi.facade.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.facade.request.user.forgotpassword.ForgotPasswordQueryRequest;
import id.thesis.shumishumi.facade.request.user.forgotpassword.ForgotPasswordSendRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.user.forgotpassword.ForgotPasswordQueryResult;
import id.thesis.shumishumi.facade.result.user.forgotpassword.ForgotPasswordSendResult;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordFacadeImpl extends ProcessFacade implements ForgotPasswordFacade {
    @Override
    public ForgotPasswordSendResult send(ForgotPasswordSendRequest request) {
        return (ForgotPasswordSendResult) ProcessCallbackSupport.process(ProcessTypeEnum.FORGOT_PASSWORD, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new ForgotPasswordSendResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public ForgotPasswordQueryResult query(ForgotPasswordQueryRequest request) {
        return null;
    }
}
