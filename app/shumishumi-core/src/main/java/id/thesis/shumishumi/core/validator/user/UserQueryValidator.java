/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.validator.user;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.user.UserQueryRequest;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: UserQueryValidator.java, v 0.1 2022‐12‐28 9:21 AM Aldih Suhandi Exp $$
 */
public class UserQueryValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof UserQueryRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        UserQueryRequest queryRequest = (UserQueryRequest) baseRequest;
        ParamChecker.isNotEmpty(queryRequest.getKey(), "key", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isNotEmpty(queryRequest.getIdentifier(), "identifier", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        String identifier = queryRequest.getIdentifier();
        boolean identifierValid = DatabaseConst.USER_ID.equals(identifier) ||
                DatabaseConst.EMAIL.equals(identifier) || DatabaseConst.PHONE_NUMBER.equals(identifier);
        ParamChecker.isExpected(identifierValid, "identifier", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
