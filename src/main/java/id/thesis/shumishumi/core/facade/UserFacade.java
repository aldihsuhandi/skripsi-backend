package id.thesis.shumishumi.core.facade;

import id.thesis.shumishumi.core.request.user.UserActivateRequest;
import id.thesis.shumishumi.core.request.user.UserForgotPasswordRequest;
import id.thesis.shumishumi.core.request.user.UserLoginRequest;
import id.thesis.shumishumi.core.request.user.UserQueryRequest;
import id.thesis.shumishumi.core.request.user.UserRegisterRequest;
import id.thesis.shumishumi.core.request.user.UserUpdateRequest;
import id.thesis.shumishumi.core.result.user.UserActivateResult;
import id.thesis.shumishumi.core.result.user.UserForgotPasswordResult;
import id.thesis.shumishumi.core.result.user.UserLoginResult;
import id.thesis.shumishumi.core.result.user.UserQueryResult;
import id.thesis.shumishumi.core.result.user.UserRegisterResult;
import id.thesis.shumishumi.core.result.user.UserUpdateResult;

public interface UserFacade {
    UserRegisterResult register(UserRegisterRequest request);

    UserLoginResult login(UserLoginRequest request);

    UserUpdateResult update(UserUpdateRequest request);

    UserQueryResult query(UserQueryRequest request);

    UserActivateResult activate(UserActivateRequest request);

    UserForgotPasswordResult forgotPassword(UserForgotPasswordRequest request);
}
