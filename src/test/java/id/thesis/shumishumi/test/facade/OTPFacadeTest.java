package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.common.model.enumeration.OTPTypeEnum;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.facade.OTPFacade;
import id.thesis.shumishumi.foundation.dalgen.model.result.ContentDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.OtpDO;
import id.thesis.shumishumi.core.request.otp.OTPSendRequest;
import id.thesis.shumishumi.core.request.otp.OTPValidateRequest;
import id.thesis.shumishumi.core.result.otp.OTPSendResult;
import id.thesis.shumishumi.core.result.otp.OTPValidateResult;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class OTPFacadeTest extends FacadeTestBase {

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
        Mockito.when(otpDAO.query(Mockito.any())).thenReturn(mockOTPDO(new Date(), true));
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
        Mockito.when(otpDAO.query(Mockito.any())).thenReturn(mockOTPDO(new Date(), true));
        Mockito.when(contentDAO.queryContent(Mockito.any())).thenReturn(mockContentDO("%s %s"));
        Mockito.when(javaMailSender.createMimeMessage()).thenReturn(message);

        OTPSendResult result = otpFacade.sendOtp(request);
        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.PARAM_ILLEGAL.getErrorCode());
    }

    @Test
    public void validateOTPTest_SUCCESS() {
        OTPValidateRequest request = new OTPValidateRequest();
        request.setEmail("email@email.com");
        request.setOtp("1234567890");
        request.setOtpType(OTPTypeEnum.USER_ACTIVATION.getName());


        Date otpDt = Date.from(LocalDateTime.now().plus(Duration.
                of(10, ChronoUnit.MINUTES)).atZone(ZoneId.systemDefault()).toInstant());

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO("password", true));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO());
        Mockito.when(otpDAO.query(Mockito.any())).thenReturn(mockOTPDO(otpDt, true));

        OTPValidateResult result = otpFacade.validateOtp(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    private ContentDO mockContentDO(String content) {
        ContentDO contentDO = new ContentDO();
        contentDO.setContent(content);
        contentDO.setContentName("content name");

        return contentDO;
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
