package id.thesis.shumishumi.core.validator.user;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.user.UserForgotPasswordRequest;

public class UserForgotPasswordValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof UserForgotPasswordRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        UserForgotPasswordRequest request = (UserForgotPasswordRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getEmail(), "email", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(request.getEmail(),
                "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
                "email", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ParamChecker.isNotEmpty(request.getOtp(), "otp", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        String password = request.getPassword();

        ParamChecker.isNotEmpty(password, "password", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(password.length() >= 8, "password", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(password, "^(?:[\\d,\\/().]*[a-zA-Z][a-zA-Z\\d,\\/().]*)?$", "password", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
