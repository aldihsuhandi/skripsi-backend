/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.service.api;

import id.thesis.shumishumi.core.callback.ProcessCallback;
import id.thesis.shumishumi.core.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.facade.api.ClientFacade;
import id.thesis.shumishumi.facade.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.facade.request.client.ClientAuthRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.client.ClientAuthResult;
import org.springframework.stereotype.Service;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
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
