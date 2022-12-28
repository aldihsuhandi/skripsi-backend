package id.thesis.shumishumi.core.facade;

import id.thesis.shumishumi.rest.request.user.UserLoginRequest;
import id.thesis.shumishumi.rest.request.user.UserQueryRequest;
import id.thesis.shumishumi.rest.request.user.UserRegisterRequest;
import id.thesis.shumishumi.rest.request.user.UserUpdateRequest;
import id.thesis.shumishumi.rest.result.user.UserLoginResult;
import id.thesis.shumishumi.rest.result.user.UserQueryResult;
import id.thesis.shumishumi.rest.result.user.UserRegisterResult;
import id.thesis.shumishumi.rest.result.user.UserUpdateResult;

public interface UserFacade {
    UserRegisterResult register(UserRegisterRequest request);

    UserLoginResult login(UserLoginRequest request);

    UserUpdateResult update(UserUpdateRequest body);

    UserQueryResult query(UserQueryRequest request);
}
