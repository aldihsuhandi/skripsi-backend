package id.thesis.shumishumi.rest.controller;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.core.facade.UserFacade;
import id.thesis.shumishumi.process.callback.ControllerCallback;
import id.thesis.shumishumi.process.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.rest.request.HtmlRequest;
import id.thesis.shumishumi.rest.request.user.UserLoginRequest;
import id.thesis.shumishumi.rest.request.user.UserRegisterRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import id.thesis.shumishumi.rest.result.user.UserLoginResult;
import id.thesis.shumishumi.rest.result.user.UserRegisterResult;
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
            public void authCheck() throws ShumishumiException {
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
            public void authCheck() throws ShumishumiException {
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
}
