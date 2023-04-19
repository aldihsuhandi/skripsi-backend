package id.thesis.shumishumi.core.processor.otp;

import id.thesis.shumishumi.common.service.OTPService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.enumeration.OTPTypeEnum;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.otp.OTPSendRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

public class OtpSendProcessor implements BaseProcessor {

    @Autowired
    private OTPService otpService;

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        OTPSendRequest sendRequest = (OTPSendRequest) baseRequest;

        UserVO userVO = userService.queryByEmail(sendRequest.getEmail(), true);

        AssertUtil.isNotNull(userVO, "user not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(!userVO.isDeleted(), "user not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(checkActiveUser(sendRequest.getOtpType(), userVO.isActive()), "user is already active", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        otpService.send(sendRequest.getEmail(), sendRequest.getOtpType());
    }

    private boolean checkActiveUser(String otpType, boolean isActive) {
        return (OTPTypeEnum.FORGOT_PASSWORD.getName().equals(otpType) && isActive) ||
                (OTPTypeEnum.USER_ACTIVATION.getName().equals(otpType) && !isActive);
    }
}
