package id.thesis.shumishumi.core.validator.item;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.item.UpdateItemRequest;

public class UpdateItemValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "baseRequest", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof UpdateItemRequest, "baseRequest", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        UpdateItemRequest request = (UpdateItemRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getItemId(), "itemId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isNotNull(request.getItemUpdateContext(), "itemUpdateContext", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
