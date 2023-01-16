package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.model.context.UserUpdateContext;
import id.thesis.shumishumi.common.model.enumeration.OTPTypeEnum;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.facade.UserFacade;
import id.thesis.shumishumi.dalgen.model.result.OtpDO;
import id.thesis.shumishumi.dalgen.model.result.RoleDO;
import id.thesis.shumishumi.dalgen.model.result.SessionDO;
import id.thesis.shumishumi.dalgen.model.result.UserDO;
import id.thesis.shumishumi.rest.request.user.UserActivateRequest;
import id.thesis.shumishumi.rest.request.user.UserForgotPasswordRequest;
import id.thesis.shumishumi.rest.request.user.UserLoginRequest;
import id.thesis.shumishumi.rest.request.user.UserQueryRequest;
import id.thesis.shumishumi.rest.request.user.UserRegisterRequest;
import id.thesis.shumishumi.rest.request.user.UserUpdateRequest;
import id.thesis.shumishumi.rest.result.user.UserActivateResult;
import id.thesis.shumishumi.rest.result.user.UserForgotPasswordResult;
import id.thesis.shumishumi.rest.result.user.UserLoginResult;
import id.thesis.shumishumi.rest.result.user.UserQueryResult;
import id.thesis.shumishumi.rest.result.user.UserRegisterResult;
import id.thesis.shumishumi.rest.result.user.UserUpdateResult;
import id.thesis.shumishumi.test.TestBase;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class UserFacadeTest extends TestBase {
    @Autowired
    private UserFacade userFacade;

    @Test
    public void userRegisterTest_SUCCESS() {
        UserRegisterRequest registerRequest = new UserRegisterRequest();
        registerRequest.setUsername("username");
        registerRequest.setEmail("email@email.com");
        registerRequest.setPassword("password");
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
        registerRequest.setPhoneNumber("12345");

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(userDAO.queryByPhoneNumber(Mockito.any())).thenReturn(null);
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO());

        UserRegisterResult result = userFacade.register(registerRequest);
        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.USER_ALREADY_EXIST.getErrorCode());
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
        UserUpdateContext context = new UserUpdateContext();
        context.setPassword("password2");
        context.setUsername("username");

        request.setPassword("password");
        request.setUserUpdateContext(context);
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
        UserUpdateContext context = new UserUpdateContext();
        context.setPassword("password2");
        context.setUsername("username");

        request.setPassword(FunctionUtil.hashPassword("password"));
        request.setUserUpdateContext(context);
        request.setSessionId("sessionId");

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(null);

        UserUpdateResult result = userFacade.update(request);

        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
    }

    @Test
    public void userUpdateTest_FAILED_authentication_failed() {
        UserUpdateRequest request = new UserUpdateRequest();
        UserUpdateContext context = new UserUpdateContext();
        context.setPassword("password");
        context.setUsername("username");

        request.setPassword("this is a password");
        request.setUserUpdateContext(context);
        request.setSessionId("sessionId");

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
        UserUpdateContext context = new UserUpdateContext();
        context.setPassword("password2");
        context.setUsername("username");
        context.setEmail("email@email.com");

        request.setPassword("password");
        request.setUserUpdateContext(context);
        request.setSessionId("sessionId");

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

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO("password"));
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

    private UserDO mockUserDO(String password) {
        UserDO user = new UserDO();
        String hashPassword = FunctionUtil.hashPassword(password);
        user.setUserId("userId");
        user.setEmail("email");
        user.setActive(true);
        user.setPassword(hashPassword);

        return user;
    }

    private RoleDO mockRoleDO() {
        RoleDO roleDO = new RoleDO();
        roleDO.setRoleId("roleId");
        roleDO.setRoleName("roleName");
        roleDO.setGmtCreate(new Date());
        roleDO.setGmtModified(new Date());

        return roleDO;
    }

    private SessionDO mockSessionDO() {
        SessionDO sessionDO = new SessionDO();
        sessionDO.setUserId("userId");
        sessionDO.setRemembered(true);
        sessionDO.setActive(true);

        return sessionDO;
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
