/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.process.processor.client;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.viewobject.ClientVO;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.service.ClientService;
import id.thesis.shumishumi.process.processor.BaseProcessor;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.client.ClientAuthRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
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
