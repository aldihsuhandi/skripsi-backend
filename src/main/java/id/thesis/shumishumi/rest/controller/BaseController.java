/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.rest.controller;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.context.Headers;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.core.facade.ClientFacade;
import id.thesis.shumishumi.core.request.client.ClientAuthRequest;
import id.thesis.shumishumi.core.result.client.ClientAuthResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: BaseController.java, v 0.1 2022‐12‐26 3:10 PM Aldih Suhandi Exp $$
 */
public class BaseController {
    @Autowired
    private ClientFacade clientFacade;

    public void authenticate(Headers headers) throws ShumishumiException {
        if (headers == null) {
            throw new ShumishumiException("API Authentication failed", ShumishumiErrorCodeEnum.OAUTH_ERROR);
        }

        ClientAuthRequest request = new ClientAuthRequest();
        request.setClientId(headers.getClientId());
        request.setClientSecret(headers.getClientSecret());

        ClientAuthResult result = clientFacade.authenticate(request);

        if (!result.getResultContext().isSuccess()) {
            throw new ShumishumiException("API Authentication failed", ShumishumiErrorCodeEnum.OAUTH_ERROR);
        }
    }

}
