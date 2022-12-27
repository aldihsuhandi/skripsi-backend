/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.process.processor.session;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.process.processor.BaseProcessor;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.session.SessionLogoutRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: SessionLogoutProcessor.java, v 0.1 2022‐12‐27 7:50 AM Aldih Suhandi Exp $$
 */
public class SessionLogoutProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Override
    public void doProcess(BaseResult result, BaseRequest request) throws ShumishumiException {
        SessionLogoutRequest logoutRequest = (SessionLogoutRequest) request;
        sessionService.logout(logoutRequest.getSessionId());
    }
}
