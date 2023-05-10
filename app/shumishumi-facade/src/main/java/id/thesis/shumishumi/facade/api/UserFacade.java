package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.user.*;
import id.thesis.shumishumi.facade.request.user.email.EmailDecryptRequest;
import id.thesis.shumishumi.facade.request.user.email.EmailEncryptRequest;
import id.thesis.shumishumi.facade.result.user.*;
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
