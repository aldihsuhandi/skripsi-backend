package id.thesis.shumishumi.core.validator.dictionary;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.dictionary.DictionaryQueryRequest;

public class DictionaryQueryValidator implements BaseValidator {

    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof DictionaryQueryRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        DictionaryQueryRequest request = (DictionaryQueryRequest) baseRequest;
        ParamChecker.isNotNull(request.getDictionaryKey(), "dictionaryKey", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
