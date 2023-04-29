package id.thesis.shumishumi.core.validator.user.email;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.user.email.EmailEncryptRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailEncryptValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof EmailEncryptRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        EmailEncryptRequest request = (EmailEncryptRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getEmail(), "email", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
