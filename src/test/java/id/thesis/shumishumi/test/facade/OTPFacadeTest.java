package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.common.model.enumeration.OTPTypeEnum;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.facade.OTPFacade;
import id.thesis.shumishumi.dalgen.model.result.ContentDO;
import id.thesis.shumishumi.dalgen.model.result.OtpDO;
import id.thesis.shumishumi.dalgen.model.result.RoleDO;
import id.thesis.shumishumi.dalgen.model.result.UserDO;
import id.thesis.shumishumi.rest.request.otp.OTPSendRequest;
import id.thesis.shumishumi.rest.result.otp.OTPSendResult;
import id.thesis.shumishumi.test.TestBase;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;
import java.util.Date;

public class OTPFacadeTest extends TestBase {

    @Autowired
    private OTPFacade otpFacade;

    @MockBean
    private JavaMailSender javaMailSender;

    @Mock
    private MimeMessage message;

    @Test
    public void sendOTPTest_SUCCESS() {
        OTPSendRequest request = new OTPSendRequest();
        request.setEmail("email@email.com");
        request.setOtpType(OTPTypeEnum.FORGOT_PASSWORD.getName());

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO("password", true));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO());
        Mockito.when(otpDAO.query(Mockito.any())).thenReturn(mockOTPDO());
        Mockito.when(contentDAO.queryContent(Mockito.any())).thenReturn(mockContentDO("%s %s"));
        Mockito.when(javaMailSender.createMimeMessage()).thenReturn(message);

        OTPSendResult result = otpFacade.sendOtp(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void sendOTPTest_FAILED_userAlreadyActivated() {
        OTPSendRequest request = new OTPSendRequest();
        request.setEmail("email@email.com");
        request.setOtpType(OTPTypeEnum.USER_ACTIVATION.getName());

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO("password", true));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO());
        Mockito.when(otpDAO.query(Mockito.any())).thenReturn(mockOTPDO());
        Mockito.when(contentDAO.queryContent(Mockito.any())).thenReturn(mockContentDO("%s %s"));
        Mockito.when(javaMailSender.createMimeMessage()).thenReturn(message);

        OTPSendResult result = otpFacade.sendOtp(request);
        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), ShumishumiErrorCodeEnum.PARAM_ILLEGAL.getErrorCode());
    }

    private ContentDO mockContentDO(String content) {
        ContentDO contentDO = new ContentDO();
        contentDO.setContent(content);
        contentDO.setContentName("content name");

        return contentDO;
    }

    private OtpDO mockOTPDO() {
        OtpDO otpDO = new OtpDO();
        otpDO.setOtpId("otpId");
        otpDO.setOtp(FunctionUtil.generateOtp(10, true, true));
        otpDO.setOtpDt(new Date());
        otpDO.setTypeId(OTPTypeEnum.FORGOT_PASSWORD.getId());

        return otpDO;
    }

    private UserDO mockUserDO(String password, boolean isActive) {
        UserDO user = new UserDO();
        String hashPassword = FunctionUtil.hashPassword(password);
        user.setUserId("userId");
        user.setEmail("email");
        user.setActive(isActive);
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
}
