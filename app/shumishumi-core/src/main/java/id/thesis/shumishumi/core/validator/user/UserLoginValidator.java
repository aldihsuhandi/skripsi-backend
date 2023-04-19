package id.thesis.shumishumi.core.validator.user;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.user.UserLoginRequest;

public class UserLoginValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof UserLoginRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        UserLoginRequest loginRequest = (UserLoginRequest) baseRequest;
        ParamChecker.isNotEmpty(loginRequest.getEmail(), "email", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isNotEmpty(loginRequest.getPassword(), "password", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
