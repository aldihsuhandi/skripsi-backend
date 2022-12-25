package id.thesis.shumishumi.process.service;

import id.thesis.shumishumi.common.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.core.facade.UserFacade;
import id.thesis.shumishumi.process.callback.ProcessCallback;
import id.thesis.shumishumi.process.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.rest.request.user.UserRegisterRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import id.thesis.shumishumi.rest.result.user.UserRegisterResult;
import org.springframework.stereotype.Service;

@Service
public class UserFacadeImpl extends ProcessFacade implements UserFacade {
    @Override
    public UserRegisterResult register(UserRegisterRequest request) {
        return (UserRegisterResult) ProcessCallbackSupport.process(ProcessTypeEnum.USER_REGISTER, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new UserRegisterResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) throws Exception {
                doProcess(request, result, processType);
            }
        });
    }
}
