package id.thesis.shumishumi.core.facade;

import id.thesis.shumishumi.rest.request.user.UserLoginRequest;
import id.thesis.shumishumi.rest.request.user.UserRegisterRequest;
import id.thesis.shumishumi.rest.result.user.UserLoginResult;
import id.thesis.shumishumi.rest.result.user.UserRegisterResult;

public interface UserFacade {
    UserRegisterResult register(UserRegisterRequest request);

    UserLoginResult login(UserLoginRequest request);
}
