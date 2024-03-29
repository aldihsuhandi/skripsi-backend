/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.service.api;

import id.thesis.shumishumi.core.callback.ProcessCallback;
import id.thesis.shumishumi.core.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.facade.api.SessionFacade;
import id.thesis.shumishumi.facade.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.facade.request.session.SessionLogoutRequest;
import id.thesis.shumishumi.facade.request.session.SessionQueryRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.session.SessionLogoutResult;
import id.thesis.shumishumi.facade.result.session.SessionQueryResult;
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
