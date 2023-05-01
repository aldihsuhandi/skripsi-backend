package id.thesis.shumishumi.web.controller;

import id.thesis.shumishumi.common.model.form.user.UserActivateForm;
import id.thesis.shumishumi.common.model.form.user.UserInfoForm;
import id.thesis.shumishumi.common.model.form.user.UserLoginForm;
import id.thesis.shumishumi.common.model.form.user.UserRegisterForm;
import id.thesis.shumishumi.common.model.form.user.UserResetPasswordForm;
import id.thesis.shumishumi.common.model.form.user.UserUpdateForm;
import id.thesis.shumishumi.common.model.form.user.email.EmailDecryptForm;
import id.thesis.shumishumi.common.model.form.user.email.EmailEncryptForm;
import id.thesis.shumishumi.common.model.form.user.forgotpassword.ForgotPasswordForm;
import id.thesis.shumishumi.common.model.form.user.forgotpassword.ForgotPasswordQueryForm;
import id.thesis.shumishumi.core.callback.ControllerCallback;
import id.thesis.shumishumi.core.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.facade.api.ForgotPasswordFacade;
import id.thesis.shumishumi.facade.api.UserFacade;
import id.thesis.shumishumi.facade.request.user.UserActivateRequest;
import id.thesis.shumishumi.facade.request.user.UserLoginRequest;
import id.thesis.shumishumi.facade.request.user.UserQueryRequest;
import id.thesis.shumishumi.facade.request.user.UserRegisterRequest;
import id.thesis.shumishumi.facade.request.user.UserResetPasswordRequest;
import id.thesis.shumishumi.facade.request.user.UserUpdateRequest;
import id.thesis.shumishumi.facade.request.user.email.EmailDecryptRequest;
import id.thesis.shumishumi.facade.request.user.email.EmailEncryptRequest;
import id.thesis.shumishumi.facade.request.user.forgotpassword.ForgotPasswordQueryRequest;
import id.thesis.shumishumi.facade.request.user.forgotpassword.ForgotPasswordSendRequest;
import id.thesis.shumishumi.facade.result.user.UserActivateResult;
import id.thesis.shumishumi.facade.result.user.UserLoginResult;
import id.thesis.shumishumi.facade.result.user.UserQueryResult;
import id.thesis.shumishumi.facade.result.user.UserRegisterResult;
import id.thesis.shumishumi.facade.result.user.UserResetPasswordResult;
import id.thesis.shumishumi.facade.result.user.UserUpdateResult;
import id.thesis.shumishumi.facade.result.user.email.EmailDecryptResult;
import id.thesis.shumishumi.facade.result.user.email.EmailEncryptResult;
import id.thesis.shumishumi.facade.result.user.forgotpassword.ForgotPasswordQueryResult;
import id.thesis.shumishumi.facade.result.user.forgotpassword.ForgotPasswordSendResult;
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

    @Autowired
    private ForgotPasswordFacade forgotPasswordFacade;

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

    @PostMapping("/forgot_password/send")
    public ResponseEntity<ForgotPasswordSendResult> forgotPassword(@RequestHeader HttpHeaders httpHeaders, @RequestBody ForgotPasswordForm form) {
        return ControllerCallbackSupport.process(httpHeaders, form, MediaType.APPLICATION_JSON, new ControllerCallback<ForgotPasswordSendResult, ForgotPasswordSendRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public ForgotPasswordSendRequest composeRequest() {
                ForgotPasswordSendRequest request = new ForgotPasswordSendRequest();
                request.setEmail(form.getEmail());

                return request;
            }

            @Override
            public ForgotPasswordSendResult doProcess(ForgotPasswordSendRequest request) {
                return forgotPasswordFacade.send(request);
            }
        });
    }

    @PostMapping("/forgot_password/query")
    public ResponseEntity<ForgotPasswordQueryResult> forgotPasswordQuery(@RequestHeader HttpHeaders headers, @RequestBody ForgotPasswordQueryForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<ForgotPasswordQueryResult, ForgotPasswordQueryRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public ForgotPasswordQueryRequest composeRequest() {
                ForgotPasswordQueryRequest request = new ForgotPasswordQueryRequest();
                request.setUuid(form.getUuid());

                return request;
            }

            @Override
            public ForgotPasswordQueryResult doProcess(ForgotPasswordQueryRequest request) {
                return forgotPasswordFacade.query(request);
            }
        });
    }

    @PostMapping("/reset_password")
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

    @PostMapping("/email/encrypt")
    public ResponseEntity<EmailEncryptResult> emailEncrypt(@RequestHeader HttpHeaders headers, @RequestBody EmailEncryptForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<EmailEncryptResult, EmailEncryptRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public EmailEncryptRequest composeRequest() {
                EmailEncryptRequest request = new EmailEncryptRequest();
                request.setEmail(form.getEmail());

                return request;
            }

            @Override
            public EmailEncryptResult doProcess(EmailEncryptRequest request) {
                return userFacade.emailEncrypt(request);
            }
        });
    }

    @PostMapping("/email/decrypt")
    public ResponseEntity<EmailDecryptResult> emailDecrypt(@RequestHeader HttpHeaders headers, @RequestBody EmailDecryptForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<EmailDecryptResult, EmailDecryptRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public EmailDecryptRequest composeRequest() {
                EmailDecryptRequest request = new EmailDecryptRequest();
                request.setUuid(form.getUuid());

                return request;
            }

            @Override
            public EmailDecryptResult doProcess(EmailDecryptRequest request) {
                return userFacade.emailDecrypt(request);
            }
        });
    }
}
