/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.process.processor.session;

import id.thesis.shumishumi.common.process.processor.BaseProcessor;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.session.SessionLogoutRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: SessionLogoutProcessor.java, v 0.1 2022‐12‐27 7:50 AM Aldih Suhandi Exp $$
 */
public class SessionLogoutProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        SessionLogoutRequest logoutRequest = (SessionLogoutRequest) baseRequest;
        sessionService.logout(logoutRequest.getSessionId());
    }
}
