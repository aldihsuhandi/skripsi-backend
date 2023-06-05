/**
 * Dana.id
 * Copyright (c) 2017-2023 All Rights Reserved.
 */
package id.thesis.shumishumi.core.validator.item;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.item.DeleteItemRequest;

/**
 * @author Aldih Suhandi <i-aldih.suhandi@dana.id>
 * @version $Id: DeleteItemValidator.java, v 0.1 2023‐06‐05 09.50 Aldih Suhandi Exp $$
 */
public class DeleteItemValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof DeleteItemRequest,
                "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        DeleteItemRequest request = (DeleteItemRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getItemId(), "itemId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
