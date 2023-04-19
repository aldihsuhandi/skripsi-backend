package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.facade.api.UserFacade;
import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.facade.model.enumeration.OTPTypeEnum;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
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
import id.thesis.shumishumi.foundation.model.result.OtpDO;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class UserFacadeTest extends FacadeTestBase {
    @Autowired
    private UserFacade userFacade;

    @Test
    public void userRegisterTest_SUCCESS() {
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setEmail("email@email.com");
        registerRequest.setPassword("password");
        registerRequest.setConfirmPassword("password");
        registerRequest.setPhoneNumber("12345");

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(null);
        Mockito.when(userDAO.queryByPhoneNumber(Mockito.any())).thenReturn(null);

        UserRegisterResult result = userFacade.register(registerRequest);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }

    @Test
    public void userRegisterTest_FAILED() {
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setEmail("email@email.com");
        registerRequest.setPassword("password");
        registerRequest.setConfirmPassword("password");
        registerRequest.setPhoneNumber("12345");

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO("password", false, false));
        Mockito.when(userDAO.queryByPhoneNumber(Mockito.any())).thenReturn(null);
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO());

        UserRegisterResult result = userFacade.register(registerRequest);
        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.USER_ALREADY_EXIST.getErrorCode());
    }

    @Test
    public void userRegisterTest_FAILED_confirmPasswordEmpty() {
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setEmail("email@email.com");
        registerRequest.setPassword("password");
        registerRequest.setPhoneNumber("12345");

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(userDAO.queryByPhoneNumber(Mockito.any())).thenReturn(null);
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO());

        UserRegisterResult result = userFacade.register(registerRequest);
        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.PARAM_ILLEGAL.getErrorCode());
    }

    @Test
    public void userLoginTest_SUCCESS() {
        UserLoginRequest request = new UserLoginRequest();
        String password = "password";
        request.setEmail("email@email.com");
        request.setPassword(password);

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO(password));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO());

        UserLoginResult result = userFacade.login(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }

    @Test
    public void userLoginTest_FAILED_wrong_password() {
        UserLoginRequest request = new UserLoginRequest();
        String password = "password";
        request.setEmail("email@email.com");
        request.setPassword(password);

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO("password2"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO());

        UserLoginResult result = userFacade.login(request);
        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.AUTHENTICATION_FAILED.getErrorCode());
    }

    @Test
    public void userLoginTest_FAILED_user_not_exist() {
        UserLoginRequest request = new UserLoginRequest();
        String password = "password";
        request.setEmail("email@email.com");
        request.setPassword(password);

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(null);

        UserLoginResult result = userFacade.login(request);
        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.USER_NOT_FOUND.getErrorCode());
    }

    @Test
    public void userUpdateTest_SUCCESS() {
        UserUpdateRequest request = new UserUpdateRequest();

        request.setPassword("password2");
        request.setConfirmPassword("password2");
        request.setUsername("username");
        request.setOldPassword("password");
        request.setSessionId("sessionId");

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO());
        Mockito.when(userDAO.queryById(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO());

        UserUpdateResult result = userFacade.update(request);

        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }

    @Test
    public void userUpdateTest_FAILED_session_expired() {
        UserUpdateRequest request = new UserUpdateRequest();

        request.setPassword(FunctionUtil.hashPassword("password"));
        request.setPassword("password2");
        request.setConfirmPassword("password2");
        request.setUsername("username");
        request.setSessionId("sessionId");

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(null);

        UserUpdateResult result = userFacade.update(request);

        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
    }

    @Test
    public void userUpdateTest_FAILED_authentication_failed() {
        UserUpdateRequest request = new UserUpdateRequest();

        request.setOldPassword("this is a password");
        request.setSessionId("sessionId");
        request.setPassword("password2");
        request.setConfirmPassword("password2");
        request.setUsername("username");

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO());
        Mockito.when(userDAO.queryById(Mockito.any())).thenReturn(mockUserDO("kljasdflkjasdfljk"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO());

        UserUpdateResult result = userFacade.update(request);

        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.AUTHENTICATION_FAILED.getErrorCode());
    }

    @Test
    public void userUpdateTest_FAILED_user_exist() {
        UserUpdateRequest request = new UserUpdateRequest();

        request.setOldPassword("password");
        request.setSessionId("sessionId");
        request.setPassword("password2");
        request.setConfirmPassword("password2");
        request.setEmail("email@email.com");
        request.setUsername("username");

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO());
        Mockito.when(userDAO.queryById(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO());
        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO("password"));

        UserUpdateResult result = userFacade.update(request);

        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.USER_ALREADY_EXIST.getErrorCode());
    }

    @Test
    public void userQueryTest_SUCCESS() {
        UserQueryRequest request = new UserQueryRequest();
        request.setIdentifier(DatabaseConst.EMAIL);
        request.setKey("email@email.com");
        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO());

        UserQueryResult result = userFacade.query(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());

    }

    @Test
    public void userQueryTest_FAILED_user_not_found() {
        UserQueryRequest request = new UserQueryRequest();
        request.setIdentifier(DatabaseConst.EMAIL);
        request.setKey("email@email.com");
        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(null);

        UserQueryResult result = userFacade.query(request);
        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.USER_NOT_FOUND.getErrorCode());
    }

    @Test
    public void userActivate_SUCCESS() {
        UserActivateRequest request = new UserActivateRequest();
        request.setOtp("otp");
        request.setEmail("email@email.com");

        Date otpDt = Date.from(LocalDateTime.now().plus(Duration.
                of(10, ChronoUnit.MINUTES)).atZone(ZoneId.systemDefault()).toInstant());

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO("password", false, false));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO());
        Mockito.when(otpDAO.query(Mockito.any())).thenReturn(mockOTPDO(otpDt, true));

        UserActivateResult result = userFacade.activate(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }

    @Test
    public void userActivate_FAILED_otpValidationError() {
        UserActivateRequest request = new UserActivateRequest();
        request.setOtp("otp");
        request.setEmail("email@email.com");

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO());
        Mockito.when(otpDAO.query(Mockito.any())).thenReturn(mockOTPDO(new Date(), true));

        UserActivateResult result = userFacade.activate(request);
        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.OTP_VALIDATION_ERROR.getErrorCode());
    }

    @Test
    public void userForgotPassword_SUCCESS() {
        UserForgotPasswordRequest request = new UserForgotPasswordRequest();
        request.setOtp("otp");
        request.setEmail("email@email.com");
        request.setPassword("password");

        Date otpDt = Date.from(LocalDateTime.now().plus(Duration.
                of(10, ChronoUnit.MINUTES)).atZone(ZoneId.systemDefault()).toInstant());

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO());
        Mockito.when(otpDAO.query(Mockito.any())).thenReturn(mockOTPDO(otpDt, true));

        UserForgotPasswordResult result = userFacade.forgotPassword(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }

    @Test
    public void userForgotPassword_FAILED_otpValidationError() {
        UserForgotPasswordRequest request = new UserForgotPasswordRequest();
        request.setOtp("otp");
        request.setEmail("email@email.com");
        request.setPassword("password");

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO());
        Mockito.when(otpDAO.query(Mockito.any())).thenReturn(mockOTPDO(new Date(), true));

        UserForgotPasswordResult result = userFacade.forgotPassword(request);
        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.OTP_VALIDATION_ERROR.getErrorCode());
    }

    private OtpDO mockOTPDO(Date otpDt, boolean isActive) {
        OtpDO otpDO = new OtpDO();
        otpDO.setOtpId("otpId");
        otpDO.setOtp(FunctionUtil.generateOtp(10, true, true));
        otpDO.setActive(true);
        otpDO.setOtpDt(otpDt);
        otpDO.setTypeId(OTPTypeEnum.FORGOT_PASSWORD.getId());

        return otpDO;
    }
}
