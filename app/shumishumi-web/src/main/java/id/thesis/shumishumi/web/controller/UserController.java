package id.thesis.shumishumi.web.controller;

import id.thesis.shumishumi.common.model.form.user.UserActivateForm;
import id.thesis.shumishumi.common.model.form.user.UserForgotPasswordForm;
import id.thesis.shumishumi.common.model.form.user.UserInfoForm;
import id.thesis.shumishumi.common.model.form.user.UserLoginForm;
import id.thesis.shumishumi.common.model.form.user.UserRegisterForm;
import id.thesis.shumishumi.common.model.form.user.UserUpdateForm;
import id.thesis.shumishumi.core.callback.ControllerCallback;
import id.thesis.shumishumi.core.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.facade.api.UserFacade;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<UserRegisterResult, UserRegisterRequest>() {
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
                request.setProfilePicture(form.getProfilePicture());
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
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<UserLoginResult, UserLoginRequest>() {
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
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<UserUpdateResult, UserUpdateRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public UserUpdateRequest composeRequest() {

                UserUpdateRequest request = new UserUpdateRequest();
                request.setOldPassword(form.getOldPassword());
                request.setUsername(form.getUsername());
                request.setEmail(form.getEmail());
                request.setPhoneNumber(form.getPhoneNumber());
                request.setPassword(form.getPassword());
                request.setConfirmPassword(form.getConfirmPassword());
                request.setProfilePicture(form.getProfilePicture());
                request.setActive(form.isActive());
                request.setDeleted(form.isDeleted());

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
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<UserQueryResult, UserQueryRequest>() {
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
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<UserActivateResult, UserActivateRequest>() {
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
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<UserForgotPasswordResult, UserForgotPasswordRequest>() {
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