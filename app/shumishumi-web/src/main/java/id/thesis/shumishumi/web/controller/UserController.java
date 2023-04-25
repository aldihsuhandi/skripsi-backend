package id.thesis.shumishumi.web.controller;

import id.thesis.shumishumi.common.model.form.user.*;
import id.thesis.shumishumi.core.callback.ControllerCallback;
import id.thesis.shumishumi.core.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.facade.api.UserFacade;
import id.thesis.shumishumi.facade.request.user.*;
import id.thesis.shumishumi.facade.result.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/user/reset_password")
    public ResponseEntity<UserResetPasswordResult> resetPassword(@RequestHeader HttpHeaders headers, @RequestBody UserResetPasswordForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<UserResetPasswordResult, UserResetPasswordRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public UserResetPasswordRequest composeRequest() {
                UserResetPasswordRequest request = new UserResetPasswordRequest();
                request.setEmail(form.getEmail());
                request.setPassword(form.getPassword());
                request.setConfirmPassword(form.getConfirmPassword());

                return request;
            }

            @Override
            public UserResetPasswordResult doProcess(UserResetPasswordRequest request) {
                return userFacade.resetPassword(request);
            }
        });
    }
}
