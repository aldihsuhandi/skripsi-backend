/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.validator.session;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.session.SessionQueryRequest;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: SessionQueryValidator.java, v 0.1 2022‐12‐30 1:11 PM Aldih Suhandi Exp $$
 */
public class SessionQueryValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof SessionQueryRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        SessionQueryRequest queryRequest = (SessionQueryRequest) baseRequest;
        ParamChecker.isNotEmpty(queryRequest.getSessionId(), "sessionId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
