package id.thesis.shumishumi.test.controller;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.core.facade.UserFacade;
import id.thesis.shumishumi.core.result.user.UserLoginResult;
import id.thesis.shumishumi.core.result.user.UserRegisterResult;
import id.thesis.shumishumi.core.result.user.UserUpdateResult;
import id.thesis.shumishumi.rest.controller.UserController;
import id.thesis.shumishumi.rest.form.user.UserLoginForm;
import id.thesis.shumishumi.rest.form.user.UserRegisterForm;
import id.thesis.shumishumi.rest.form.user.UserUpdateForm;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class UserControllerTest extends ControllerTestBase {

    @Autowired
    private UserController userController = new UserController();

    @MockBean
    private UserFacade userFacade;

    @Test
    public void registerTest_SUCCESS() {
        HttpHeaders headers = mockHeaders();
        UserRegisterForm form = new UserRegisterForm();
        form.setUsername("username");
        form.setEmail("email");
        form.setPhoneNumber("phonenumber");
        form.setPassword("password");
        form.setConfirmPassword("password");

        UserRegisterResult result = new UserRegisterResult();
        result.setResultContext(mockResultContext(ShumishumiErrorCodeEnum.SUCCESS));

        Mockito.when(userFacade.register(Mockito.any())).thenReturn(result);

        ResponseEntity<UserRegisterResult> response = userController.register(headers, form);
        ResultAssert.isExpected(response.getBody().getResultContext().getResultCode(), ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }

    @Test
    public void loginTest_SUCCESS() {
        HttpHeaders headers = mockHeaders();
        UserLoginForm form = new UserLoginForm();
        form.setEmail("email");
        form.setPassword("password");

        UserLoginResult result = new UserLoginResult();
        result.setSessionId("sessionId");
        result.setResultContext(mockResultContext(ShumishumiErrorCodeEnum.SUCCESS));

        Mockito.when(userFacade.login(Mockito.any())).thenReturn(result);

        ResponseEntity<UserLoginResult> response = userController.login(headers, form);
        ResultAssert.isExpected(response.getBody().getResultContext().getResultCode(), ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }

    @Test
    public void updateTest_SUCESS() {
        HttpHeaders headers = mockHeaders();
        UserUpdateForm form = new UserUpdateForm();
        form.setEmail("new email");
        form.setOldPassword("old password");

        UserUpdateResult result = new UserUpdateResult();
        result.setResultContext(mockResultContext(ShumishumiErrorCodeEnum.SUCCESS));

        Mockito.when(userFacade.update(Mockito.any())).thenReturn(result);

        ResponseEntity<UserUpdateResult> response = userController.update(headers, form);
        ResultAssert.isExpected(response.getBody().getResultContext().getResultCode(), ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }
}
