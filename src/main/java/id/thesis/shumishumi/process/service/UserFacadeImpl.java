package id.thesis.shumishumi.process.service;

import id.thesis.shumishumi.common.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.core.facade.UserFacade;
import id.thesis.shumishumi.process.callback.ProcessCallback;
import id.thesis.shumishumi.process.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.rest.request.user.UserLoginRequest;
import id.thesis.shumishumi.rest.request.user.UserQueryRequest;
import id.thesis.shumishumi.rest.request.user.UserRegisterRequest;
import id.thesis.shumishumi.rest.request.user.UserUpdateRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import id.thesis.shumishumi.rest.result.user.UserLoginResult;
import id.thesis.shumishumi.rest.result.user.UserQueryResult;
import id.thesis.shumishumi.rest.result.user.UserRegisterResult;
import id.thesis.shumishumi.rest.result.user.UserUpdateResult;
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
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public UserLoginResult login(UserLoginRequest request) {
        return (UserLoginResult) ProcessCallbackSupport.process(ProcessTypeEnum.USER_LOGIN, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new UserLoginResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public UserUpdateResult update(UserUpdateRequest request) {
        return (UserUpdateResult) ProcessCallbackSupport.process(ProcessTypeEnum.USER_UPDATE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new UserUpdateResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public UserQueryResult query(UserQueryRequest request) {
        return (UserQueryResult) ProcessCallbackSupport.process(ProcessTypeEnum.USER_QUERY, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new UserQueryResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }
}
