package id.thesis.shumishumi.process.processor.otp;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.viewobject.OtpVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.service.OTPService;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.process.processor.BaseProcessor;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.otp.OTPValidateRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import id.thesis.shumishumi.rest.result.otp.OTPValidateResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class OtpValidateProcessor implements BaseProcessor {

    @Autowired
    private UserService userService;

    @Autowired
    private OTPService otpService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        OTPValidateRequest validateRequest = (OTPValidateRequest) baseRequest;
        OTPValidateResult validateResult = (OTPValidateResult) baseResult;
        UserVO userVO = userService.queryByEmail(validateRequest.getEmail(), true);
        AssertUtil.isNotNull(userVO, "user not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);

        OtpVO otpVO = otpService.query(validateRequest.getEmail(), validateRequest.getOtpType(), validateRequest.getOtp());
        AssertUtil.isNotNull(otpVO, "otp not found", ShumishumiErrorCodeEnum.OTP_NOT_EXIST);

        boolean res = userVO.isActive();
        res &= !userVO.isDeleted();
        res &= otpVO.isActive();
        res &= otpVO.getOtpDt().after(new Date());

        AssertUtil.isExpected(userVO.isActive(), "user not active", ShumishumiErrorCodeEnum.USER_NOT_ACTIVE);
        AssertUtil.isExpected(!userVO.isDeleted(), "user not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(otpVO.isActive(), "otp validation error", ShumishumiErrorCodeEnum.OTP_VALIDATION_ERROR);
        AssertUtil.isExpected(otpVO.getOtpDt().after(new Date()), "otp validation error", ShumishumiErrorCodeEnum.OTP_VALIDATION_ERROR);
        validateResult.setActive(res);
    }
}
