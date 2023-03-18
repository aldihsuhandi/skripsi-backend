/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.process.service;

import id.thesis.shumishumi.common.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.core.facade.SessionFacade;
import id.thesis.shumishumi.process.callback.ProcessCallback;
import id.thesis.shumishumi.process.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.rest.request.session.SessionLogoutRequest;
import id.thesis.shumishumi.rest.request.session.SessionQueryRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import id.thesis.shumishumi.rest.result.session.SessionLogoutResult;
import id.thesis.shumishumi.rest.result.session.SessionQueryResult;
import org.springframework.stereotype.Service;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: SessionFacadeImpl.java, v 0.1 2022‐12‐27 7:46 AM Aldih Suhandi Exp $$
 */
@Service
public class SessionFacadeImpl extends ProcessFacade implements SessionFacade {
    @Override
    public SessionLogoutResult logout(SessionLogoutRequest request) {
        return (SessionLogoutResult) ProcessCallbackSupport.process(ProcessTypeEnum.SESSION_LOGOUT, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new SessionLogoutResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public SessionQueryResult query(SessionQueryRequest request) {
        return (SessionQueryResult) ProcessCallbackSupport.process(ProcessTypeEnum.SESSION_QUERY, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new SessionQueryResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }
}
