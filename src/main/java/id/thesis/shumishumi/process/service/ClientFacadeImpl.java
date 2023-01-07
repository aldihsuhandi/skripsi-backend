/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.process.service;

import id.thesis.shumishumi.common.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.core.facade.ClientFacade;
import id.thesis.shumishumi.process.callback.ProcessCallback;
import id.thesis.shumishumi.process.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.rest.request.client.ClientAuthRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import id.thesis.shumishumi.rest.result.client.ClientAuthResult;
import org.springframework.stereotype.Service;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ClientFacadeImpl.java, v 0.1 2022‐12‐26 3:27 PM Aldih Suhandi Exp $$
 */
@Service
public class ClientFacadeImpl extends ProcessFacade implements ClientFacade {
    @Override
    public ClientAuthResult authenticate(ClientAuthRequest request) {
        return (ClientAuthResult) ProcessCallbackSupport.process(ProcessTypeEnum.CLIENT_AUTH, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new ClientAuthResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }
}
