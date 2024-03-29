/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.processor.client;

import id.thesis.shumishumi.common.service.ClientService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.ClientVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.client.ClientAuthRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: AuthenticateClientProcessor.java, v 0.1 2022‐12‐26 3:21 PM Aldih Suhandi Exp $$
 */
public class ClientAuthProcessor implements BaseProcessor {

    @Autowired
    private ClientService clientService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        ClientAuthRequest authRequest = (ClientAuthRequest) baseRequest;
        ClientVO clientVO = clientService.queryById(authRequest.getClientId());
        AssertUtil.isNotNull(clientVO, "there is not client with that clientId", ShumishumiErrorCodeEnum.OAUTH_ERROR);

        AssertUtil.isExpected(clientVO.getClientSecret().equals(authRequest.getClientSecret()),
                "client secret doesn't match", ShumishumiErrorCodeEnum.OAUTH_ERROR);
    }
}
