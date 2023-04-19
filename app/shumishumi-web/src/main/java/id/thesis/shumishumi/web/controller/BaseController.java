/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.web.controller;

import id.thesis.shumishumi.facade.api.ClientFacade;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.client.ClientAuthRequest;
import id.thesis.shumishumi.facade.result.client.ClientAuthResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: BaseController.java, v 0.1 2022‐12‐26 3:10 PM Aldih Suhandi Exp $$
 */
public class BaseController {
    @Autowired
    private ClientFacade clientFacade;

    public void authenticate(String clientId, String clientSecret) throws ShumishumiException {
        ClientAuthRequest request = new ClientAuthRequest();
        request.setClientId(clientId);
        request.setClientSecret(clientSecret);

        ClientAuthResult result = clientFacade.authenticate(request);

        if (!result.getResultContext().isSuccess()) {
            throw new ShumishumiException("API Authentication failed", ShumishumiErrorCodeEnum.OAUTH_ERROR);
        }
    }

}
