package id.thesis.shumishumi.core.validator;

import id.thesis.shumishumi.facade.request.BaseRequest;

public interface BaseValidator {
    void validate(BaseRequest baseRequest);
}
