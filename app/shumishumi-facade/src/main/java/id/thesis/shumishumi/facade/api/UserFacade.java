package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.user.*;
import id.thesis.shumishumi.facade.result.user.*;

public interface UserFacade {
    UserRegisterResult register(UserRegisterRequest request);

    UserLoginResult login(UserLoginRequest request);

    UserUpdateResult update(UserUpdateRequest request);

    UserQueryResult query(UserQueryRequest request);

    UserActivateResult activate(UserActivateRequest request);

    UserResetPasswordResult resetPassword(UserResetPasswordRequest request);
}
