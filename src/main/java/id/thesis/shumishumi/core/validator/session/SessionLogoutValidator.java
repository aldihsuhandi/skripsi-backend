/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.validator.session;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.session.SessionLogoutRequest;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: SessionLogoutValidator.java, v 0.1 2022‐12‐27 7:51 AM Aldih Suhandi Exp $$
 */
public class SessionLogoutValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof SessionLogoutRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        SessionLogoutRequest logoutRequest = (SessionLogoutRequest) baseRequest;
        ParamChecker.isNotEmpty(logoutRequest.getSessionId(), "sessionId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
