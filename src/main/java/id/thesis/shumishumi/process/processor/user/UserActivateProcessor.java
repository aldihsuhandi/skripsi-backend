package id.thesis.shumishumi.process.processor.user;

import id.thesis.shumishumi.common.converter.UserRequestConverter;
import id.thesis.shumishumi.common.model.context.UserUpdateContext;
import id.thesis.shumishumi.common.model.enumeration.OTPTypeEnum;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.viewobject.OtpVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.service.OTPService;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.process.processor.BaseProcessor;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.user.UserActivateRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class UserActivateProcessor implements BaseProcessor {

    @Autowired
    private UserService userService;

    @Autowired
    private OTPService otpService;

    @Override
    public void doProcess(BaseResult result, BaseRequest request) {
        UserActivateRequest activateRequest = (UserActivateRequest) request;

        OtpVO otpVO = otpService.query(activateRequest.getEmail(), OTPTypeEnum.USER_ACTIVATION.getName(), activateRequest.getOtp());
        UserVO userVO = userService.queryByEmail(activateRequest.getEmail(), true);

        validateOtp(otpVO);
        validateUser(userVO);

        UserUpdateContext updateContext = new UserUpdateContext();
        updateContext.setIsActive(true);
        FunctionUtil.fillEmptyUpdateContext(updateContext, userVO);

        userService.update(UserRequestConverter.toInnerRequest(userVO.getUserId(), updateContext));
        userService.refreshCache(new ArrayList<>(Collections.singletonList(userVO.getUserId())), false);
        otpService.deactivate(otpVO.getOtpId());
    }

    private void validateUser(UserVO userVO) {
        AssertUtil.isNotNull(userVO, "user not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(!userVO.isDeleted(), "user not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(userVO.isActive(), "user already active", ShumishumiErrorCodeEnum.OTP_VALIDATION_ERROR);
    }

    private void validateOtp(OtpVO otpVO) {
        Date curr = new Date();

        AssertUtil.isNotNull(otpVO, "otp not exist", ShumishumiErrorCodeEnum.OTP_NOT_EXIST);
        AssertUtil.isExpected(otpVO.getOtpDt().after(curr), "otp code is not valid", ShumishumiErrorCodeEnum.OTP_VALIDATION_ERROR);
        AssertUtil.isExpected(otpVO.isActive(), "otp code is not valid", ShumishumiErrorCodeEnum.OTP_VALIDATION_ERROR);
    }
}