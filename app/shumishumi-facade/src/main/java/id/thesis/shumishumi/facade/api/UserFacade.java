package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.user.MerchantApplyRequest;
import id.thesis.shumishumi.facade.request.user.UserActivateRequest;
import id.thesis.shumishumi.facade.request.user.UserLoginRequest;
import id.thesis.shumishumi.facade.request.user.UserQueryRequest;
import id.thesis.shumishumi.facade.request.user.UserRegisterRequest;
import id.thesis.shumishumi.facade.request.user.UserResetPasswordRequest;
import id.thesis.shumishumi.facade.request.user.UserUpdateRequest;
import id.thesis.shumishumi.facade.request.user.email.EmailDecryptRequest;
import id.thesis.shumishumi.facade.request.user.email.EmailEncryptRequest;
import id.thesis.shumishumi.facade.result.user.MerchantApplyResult;
import id.thesis.shumishumi.facade.result.user.UserActivateResult;
import id.thesis.shumishumi.facade.result.user.UserLoginResult;
import id.thesis.shumishumi.facade.result.user.UserQueryResult;
import id.thesis.shumishumi.facade.result.user.UserRegisterResult;
import id.thesis.shumishumi.facade.result.user.UserResetPasswordResult;
import id.thesis.shumishumi.facade.result.user.UserUpdateResult;
import id.thesis.shumishumi.facade.result.user.email.EmailDecryptResult;
import id.thesis.shumishumi.facade.result.user.email.EmailEncryptResult;

public interface UserFacade {
    UserRegisterResult register(UserRegisterRequest request);

    UserLoginResult login(UserLoginRequest request);

    UserUpdateResult update(UserUpdateRequest request);

    UserQueryResult query(UserQueryRequest request);

    UserActivateResult activate(UserActivateRequest request);

    UserResetPasswordResult resetPassword(UserResetPasswordRequest request);

    EmailEncryptResult emailEncrypt(EmailEncryptRequest request);

    EmailDecryptResult emailDecrypt(EmailDecryptRequest request);

    MerchantApplyResult merchantApply(MerchantApplyRequest request);
}
