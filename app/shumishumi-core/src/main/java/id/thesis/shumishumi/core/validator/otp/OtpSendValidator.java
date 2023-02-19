package id.thesis.shumishumi.core.validator.otp;

import id.thesis.shumishumi.common.model.enumeration.OTPTypeEnum;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.otp.OTPSendRequest;

public class OtpSendValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof OTPSendRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        OTPSendRequest request = (OTPSendRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getEmail(), "email", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(request.getEmail(),
                "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
                "email", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ParamChecker.isNotEmpty(request.getOtpType(), "otpType", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        OTPTypeEnum otpType = OTPTypeEnum.findByName(request.getOtpType());
        ParamChecker.isNotNull(otpType, "otpType", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
