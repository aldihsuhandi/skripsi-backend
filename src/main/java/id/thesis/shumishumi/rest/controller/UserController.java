package id.thesis.shumishumi.rest.controller;

import id.thesis.shumishumi.common.model.context.UserUpdateContext;
import id.thesis.shumishumi.common.process.callback.ControllerCallback;
import id.thesis.shumishumi.common.process.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.facade.UserFacade;
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
import id.thesis.shumishumi.rest.form.user.UserActivateForm;
import id.thesis.shumishumi.rest.form.user.UserForgotPasswordForm;
import id.thesis.shumishumi.rest.form.user.UserInfoForm;
import id.thesis.shumishumi.rest.form.user.UserLoginForm;
import id.thesis.shumishumi.rest.form.user.UserRegisterForm;
import id.thesis.shumishumi.rest.form.user.UserUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserFacade userFacade;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResult> register(@RequestHeader HttpHeaders headers, @ModelAttribute UserRegisterForm form) {
        return ControllerCallbackSupport.process(headers, form, new ControllerCallback<UserRegisterResult, UserRegisterRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public UserRegisterRequest composeRequest() {
                UserRegisterRequest request = new UserRegisterRequest();
                request.setUsername(form.getUsername());
                request.setEmail(form.getEmail());
                request.setPhoneNumber(form.getPhoneNumber());
                request.setProfilePicture(FunctionUtil.convertToBlob(form.getProfilePicture()));
                request.setPassword(form.getPassword());
                request.setConfirmPassword(form.getConfirmPassword());

                return request;
            }

            @Override
            public UserRegisterResult doProcess(UserRegisterRequest request) {
                return userFacade.register(request);
            }
        });
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResult> login(@RequestHeader HttpHeaders headers, @RequestBody UserLoginForm form) {
        return ControllerCallbackSupport.process(headers, form, new ControllerCallback<UserLoginResult, UserLoginRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public UserLoginRequest composeRequest() {
                UserLoginRequest request = new UserLoginRequest();
                request.setEmail(form.getEmail());
                request.setPassword(form.getPassword());
                request.setRemembered(form.isRemembered());

                return request;
            }

            @Override
            public UserLoginResult doProcess(UserLoginRequest request) {
                return userFacade.login(request);
            }
        });
    }

    @PostMapping("/update")
    public ResponseEntity<UserUpdateResult> update(@RequestHeader HttpHeaders headers, @RequestBody UserUpdateForm form) {
        return ControllerCallbackSupport.process(headers, form, new ControllerCallback<UserUpdateResult, UserUpdateRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public UserUpdateRequest composeRequest() {
                UserUpdateContext context = new UserUpdateContext();
                context.setUsername(form.getUsername());
                context.setEmail(form.getEmail());
                context.setPhoneNumber(form.getPhoneNumber());
                context.setPassword(form.getPassword());
                context.setProfilePicture(FunctionUtil.convertToBlob(form.getProfilePicture()));
                context.setIsActive(form.isActive());
                context.setIsDeleted(form.isDeleted());

                UserUpdateRequest request = new UserUpdateRequest();
                request.setPassword(form.getOldPassword());
                request.setUserUpdateContext(context);

                return request;
            }

            @Override
            public UserUpdateResult doProcess(UserUpdateRequest request) {
                return userFacade.update(request);
            }
        });
    }

    @PostMapping("/info")
    public ResponseEntity<UserQueryResult> query(@RequestHeader HttpHeaders headers, @RequestBody UserInfoForm form) {
        return ControllerCallbackSupport.process(headers, form, new ControllerCallback<UserQueryResult, UserQueryRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public UserQueryRequest composeRequest() {
                UserQueryRequest request = new UserQueryRequest();
                request.setKey(form.getKey());
                request.setIdentifier(form.getIdentifier());

                return request;
            }

            @Override
            public UserQueryResult doProcess(UserQueryRequest request) {
                return userFacade.query(request);
            }
        });
    }

    @PostMapping("/activate")
    public ResponseEntity<UserActivateResult> activate(@RequestHeader HttpHeaders headers, @RequestBody UserActivateForm form) {
        return ControllerCallbackSupport.process(headers, form, new ControllerCallback<UserActivateResult, UserActivateRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public UserActivateRequest composeRequest() {
                UserActivateRequest request = new UserActivateRequest();
                request.setEmail(form.getEmail());
                request.setOtp(form.getOtp());

                return request;
            }

            @Override
            public UserActivateResult doProcess(UserActivateRequest request) {
                return userFacade.activate(request);
            }
        });
    }

    @PostMapping("/forgot")
    public ResponseEntity<UserForgotPasswordResult> forgotPassword(@RequestHeader HttpHeaders headers, @RequestBody UserForgotPasswordForm form) {
        return ControllerCallbackSupport.process(headers, form, new ControllerCallback<UserForgotPasswordResult, UserForgotPasswordRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public UserForgotPasswordRequest composeRequest() {
                UserForgotPasswordRequest request = new UserForgotPasswordRequest();
                request.setPassword(form.getPassword());
                request.setOtp(form.getOtp());
                request.setEmail(form.getEmail());

                return request;
            }

            @Override
            public UserForgotPasswordResult doProcess(UserForgotPasswordRequest request) {
                return userFacade.forgotPassword(request);
            }
        });
    }
}
