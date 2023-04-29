package id.thesis.shumishumi.core.validator.user.email;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.user.email.EmailDecryptRequest;

public class EmailDecryptValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof EmailDecryptRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        EmailDecryptRequest request = (EmailDecryptRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getUuid(), "uuid", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
