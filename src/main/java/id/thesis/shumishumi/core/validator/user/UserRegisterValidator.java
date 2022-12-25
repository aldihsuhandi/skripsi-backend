package id.thesis.shumishumi.core.validator.user;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.user.UserRegisterRequest;

public class UserRegisterValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) throws ShumishumiException {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof UserRegisterRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        UserRegisterRequest request = (UserRegisterRequest) baseRequest;
        // check email
        ParamChecker.isNotEmpty(request.getEmail(), "email", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(request.getEmail(),
                "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
                "email", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        // check username
        ParamChecker.isNotEmpty(request.getUsername(), "username", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        // check phonenumber
        ParamChecker.isNotEmpty(request.getPhoneNumber(), "phonenumber", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        // check password
        ParamChecker.isNotEmpty(request.getPassword(), "password", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
