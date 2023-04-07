package id.thesis.shumishumi.rest.controller;

import id.thesis.shumishumi.core.facade.UserFacade;
import id.thesis.shumishumi.common.process.callback.ControllerCallback;
import id.thesis.shumishumi.common.process.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.core.request.HtmlRequest;
import id.thesis.shumishumi.core.request.user.UserActivateRequest;
import id.thesis.shumishumi.core.request.user.UserForgotPasswordRequest;
import id.thesis.shumishumi.core.request.user.UserLoginRequest;
import id.thesis.shumishumi.core.request.user.UserQueryRequest;
import id.thesis.shumishumi.core.request.user.UserRegisterRequest;
import id.thesis.shumishumi.core.request.user.UserUpdateRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.result.user.UserActivateResult;
import id.thesis.shumishumi.core.result.user.UserForgotPasswordResult;
import id.thesis.shumishumi.core.result.user.UserLoginResult;
import id.thesis.shumishumi.core.result.user.UserQueryResult;
import id.thesis.shumishumi.core.result.user.UserRegisterResult;
import id.thesis.shumishumi.core.result.user.UserUpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserFacade userFacade;

    @PostMapping("/register")
    public UserRegisterResult register(@RequestBody HtmlRequest<UserRegisterRequest> request) {
        return (UserRegisterResult) ControllerCallbackSupport.process(request.getHead(), request.getBody(), new ControllerCallback() {

            @Override
            public void authCheck() {
                authenticate(request.getHead());
            }

            @Override
            public BaseResult initResult() {
                return new UserRegisterResult();
            }

            @Override
            public BaseResult doProcess() {
                return userFacade.register(request.getBody());
            }
        });
    }

    @PostMapping("/login")
    public UserLoginResult login(@RequestBody HtmlRequest<UserLoginRequest> request) {
        return (UserLoginResult) ControllerCallbackSupport.process(request.getHead(), request.getBody(), new ControllerCallback() {
            @Override
            public void authCheck() {
                authenticate(request.getHead());
            }

            @Override
            public BaseResult initResult() {
                return new UserLoginResult();
            }

            @Override
            public BaseResult doProcess() {
                return userFacade.login(request.getBody());
            }
        });
    }

    @PostMapping("/update")
    public UserUpdateResult update(@RequestBody HtmlRequest<UserUpdateRequest> request) {
        return (UserUpdateResult) ControllerCallbackSupport.process(request.getHead(), request.getBody(), new ControllerCallback() {
            @Override
            public void authCheck() {
                authenticate(request.getHead());
            }

            @Override
            public BaseResult initResult() {
                return new UserUpdateResult();
            }

            @Override
            public BaseResult doProcess() {
                return userFacade.update(request.getBody());
            }
        });
    }

    @PostMapping("/info")
    public UserQueryResult query(@RequestBody HtmlRequest<UserQueryRequest> request) {
        return (UserQueryResult) ControllerCallbackSupport.process(request.getHead(), request.getBody(), new ControllerCallback() {
            @Override
            public void authCheck() {
                authenticate(request.getHead());
            }

            @Override
            public BaseResult initResult() {
                return new UserQueryResult();
            }

            @Override
            public BaseResult doProcess() {
                return userFacade.query(request.getBody());
            }
        });
    }

    @PostMapping("/activate")
    public UserActivateResult activate(@RequestBody HtmlRequest<UserActivateRequest> request) {
        return (UserActivateResult) ControllerCallbackSupport.process(request.getHead(), request.getBody(), new ControllerCallback() {
            @Override
            public void authCheck() {
                authenticate(request.getHead());
            }

            @Override
            public BaseResult initResult() {
                return new UserActivateResult();
            }

            @Override
            public BaseResult doProcess() {
                return userFacade.activate(request.getBody());
            }
        });
    }

    @PostMapping("/forgot")
    public UserForgotPasswordResult forgotPassword(@RequestBody HtmlRequest<UserForgotPasswordRequest> request) {
        return (UserForgotPasswordResult) ControllerCallbackSupport.process(request.getHead(), request.getBody(), new ControllerCallback() {
            @Override
            public void authCheck() {
                authenticate(request.getHead());
            }

            @Override
            public BaseResult initResult() {
                return new UserForgotPasswordResult();
            }

            @Override
            public BaseResult doProcess() {
                return userFacade.forgotPassword(request.getBody());
            }
        });
    }

}
