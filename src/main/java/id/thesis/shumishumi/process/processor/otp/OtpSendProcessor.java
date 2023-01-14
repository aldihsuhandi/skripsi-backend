package id.thesis.shumishumi.process.processor.otp;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.request.otp.OTPSendInnerRequest;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.service.EmailService;
import id.thesis.shumishumi.core.service.OTPService;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.process.processor.BaseProcessor;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.otp.OTPSendRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

public class OtpSendProcessor implements BaseProcessor {

    @Autowired
    private EmailService emailService;

    @Autowired
    private OTPService otpService;

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult result, BaseRequest request) {
        OTPSendRequest sendRequest = (OTPSendRequest) request;
        String otp = FunctionUtil.generateOtp(10, true, true);

        UserVO userVO = userService.queryByEmail(sendRequest.getEmail(), true);

        AssertUtil.isNotNull(userVO, "user not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(!userVO.isDeleted(), "user not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(!userVO.isActive(), "user is already active", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        otpService.insert(sendRequest.getEmail(), sendRequest.getOtpType(), otp);
        emailService.sendOtpEmail(composeInnerRequest(sendRequest, otp));
    }

    private OTPSendInnerRequest composeInnerRequest(OTPSendRequest sendRequest, String otp) {
        OTPSendInnerRequest request = new OTPSendInnerRequest();
        request.setOtp(otp);
        request.setOtpType(sendRequest.getOtpType());
        request.setEmail(sendRequest.getEmail());

        return request;
    }
}
