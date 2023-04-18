package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.user.UserActivateRequest;
import id.thesis.shumishumi.facade.request.user.UserForgotPasswordRequest;
import id.thesis.shumishumi.facade.request.user.UserLoginRequest;
import id.thesis.shumishumi.facade.request.user.UserQueryRequest;
import id.thesis.shumishumi.facade.request.user.UserRegisterRequest;
import id.thesis.shumishumi.facade.request.user.UserUpdateRequest;
import id.thesis.shumishumi.facade.result.user.UserActivateResult;
import id.thesis.shumishumi.facade.result.user.UserForgotPasswordResult;
import id.thesis.shumishumi.facade.result.user.UserLoginResult;
import id.thesis.shumishumi.facade.result.user.UserQueryResult;
import id.thesis.shumishumi.facade.result.user.UserRegisterResult;
import id.thesis.shumishumi.facade.result.user.UserUpdateResult;

public interface UserFacade {
    UserRegisterResult register(UserRegisterRequest request);

    UserLoginResult login(UserLoginRequest request);

    UserUpdateResult update(UserUpdateRequest request);

    UserQueryResult query(UserQueryRequest request);

    UserActivateResult activate(UserActivateRequest request);

    UserForgotPasswordResult forgotPassword(UserForgotPasswordRequest request);
}
