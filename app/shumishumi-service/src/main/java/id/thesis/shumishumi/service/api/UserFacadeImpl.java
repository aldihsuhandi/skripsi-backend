package id.thesis.shumishumi.service.api;

import id.thesis.shumishumi.core.callback.ProcessCallback;
import id.thesis.shumishumi.core.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.facade.api.UserFacade;
import id.thesis.shumishumi.facade.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.facade.request.user.UserActivateRequest;
import id.thesis.shumishumi.facade.request.user.UserLoginRequest;
import id.thesis.shumishumi.facade.request.user.UserQueryRequest;
import id.thesis.shumishumi.facade.request.user.UserRegisterRequest;
import id.thesis.shumishumi.facade.request.user.UserResetPasswordRequest;
import id.thesis.shumishumi.facade.request.user.UserUpdateRequest;
import id.thesis.shumishumi.facade.request.user.email.EmailDecryptRequest;
import id.thesis.shumishumi.facade.request.user.email.EmailEncryptRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.user.UserActivateResult;
import id.thesis.shumishumi.facade.result.user.UserLoginResult;
import id.thesis.shumishumi.facade.result.user.UserQueryResult;
import id.thesis.shumishumi.facade.result.user.UserRegisterResult;
import id.thesis.shumishumi.facade.result.user.UserResetPasswordResult;
import id.thesis.shumishumi.facade.result.user.UserUpdateResult;
import id.thesis.shumishumi.facade.result.user.email.EmailDecryptResult;
import id.thesis.shumishumi.facade.result.user.email.EmailEncryptResult;
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

    @Override
    public UserActivateResult activate(UserActivateRequest request) {
        return (UserActivateResult) ProcessCallbackSupport.process(ProcessTypeEnum.USER_ACTIVATE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new UserActivateResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public UserResetPasswordResult resetPassword(UserResetPasswordRequest request) {
        return (UserResetPasswordResult) ProcessCallbackSupport.process(ProcessTypeEnum.RESET_PASSWORD, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new UserResetPasswordResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public EmailEncryptResult emailEncrypt(EmailEncryptRequest request) {
        return (EmailEncryptResult) ProcessCallbackSupport.process(ProcessTypeEnum.EMAIL_ENCRYPT, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new EmailEncryptResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public EmailDecryptResult emailDecrypt(EmailDecryptRequest request) {
        return (EmailDecryptResult) ProcessCallbackSupport.process(ProcessTypeEnum.EMAIL_DECRYPT, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new EmailDecryptResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }
}
