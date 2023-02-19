package id.thesis.shumishumi.core.validator.item;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.item.ItemApprovalRequest;

public class ItemApprovalValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof ItemApprovalRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ItemApprovalRequest request = (ItemApprovalRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getItemId(), "itemId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
