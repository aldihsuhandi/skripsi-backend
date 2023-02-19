/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.validator.client;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.client.ClientAuthRequest;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ClientAuthValidator.java, v 0.1 2022‐12‐26 3:22 PM Aldih Suhandi Exp $$
 */
public class ClientAuthValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof ClientAuthRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ClientAuthRequest clientAuthRequest = (ClientAuthRequest) baseRequest;

        ParamChecker.isNotEmpty(clientAuthRequest.getClientId(), "clientId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isNotEmpty(clientAuthRequest.getClientSecret(), "clientSecret", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
