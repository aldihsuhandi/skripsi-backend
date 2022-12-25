package id.thesis.shumishumi.core.validator;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.rest.request.BaseRequest;

public interface BaseValidator {
    void validate(BaseRequest baseRequest) throws ShumishumiException;
}
