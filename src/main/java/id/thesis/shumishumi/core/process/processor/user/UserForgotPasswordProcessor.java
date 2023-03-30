package id.thesis.shumishumi.core.process.processor.user;

import id.thesis.shumishumi.common.converter.UserRequestConverter;
import id.thesis.shumishumi.common.model.context.UserUpdateContext;
import id.thesis.shumishumi.common.model.enumeration.OTPTypeEnum;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.viewobject.OtpVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.process.processor.BaseProcessor;
import id.thesis.shumishumi.core.service.OTPService;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.user.UserForgotPasswordRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class UserForgotPasswordProcessor implements BaseProcessor {

    @Autowired
    private OTPService otpService;

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        UserForgotPasswordRequest forgotPasswordRequest = (UserForgotPasswordRequest) baseRequest;
        UserVO userVO = userService.queryByEmail(forgotPasswordRequest.getEmail(), true);
        OtpVO otpVO = otpService.query(forgotPasswordRequest.getPassword(),
                OTPTypeEnum.FORGOT_PASSWORD.getName(), forgotPasswordRequest.getOtp());

        validateOtp(otpVO);
        validateUser(userVO);

        UserUpdateContext updateContext = new UserUpdateContext();
        updateContext.setPassword(userVO.getPassword());

        FunctionUtil.fillEmptyUpdateContext(updateContext, userVO);

        otpService.deactivate(otpVO.getOtpId());
        userService.update(UserRequestConverter.toInnerRequest(userVO.getUserId(), updateContext));
        userService.refreshCache(new ArrayList<>(Collections.singletonList(userVO.getUserId())), false);
    }

    private void validateUser(UserVO userVO) {
        AssertUtil.isNotNull(userVO, "user not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(!userVO.isDeleted(), "user not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
    }

    private void validateOtp(OtpVO otpVO) {
        Date curr = new Date();

        AssertUtil.isNotNull(otpVO, "otp not exist", ShumishumiErrorCodeEnum.OTP_NOT_EXIST);
        AssertUtil.isExpected(otpVO.getOtpDt().after(curr), "otp code is not valid", ShumishumiErrorCodeEnum.OTP_VALIDATION_ERROR);
        AssertUtil.isExpected(otpVO.isActive(), "otp code is not valid", ShumishumiErrorCodeEnum.OTP_VALIDATION_ERROR);
    }
}
