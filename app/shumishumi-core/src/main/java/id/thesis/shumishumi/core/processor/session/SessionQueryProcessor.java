/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.processor.session;

import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.session.SessionQueryRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.session.SessionQueryResult;
import id.thesis.shumishumi.core.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: SessionQueryProcessor.java, v 0.1 2022‐12‐30 1:14 PM Aldih Suhandi Exp $$
 */
public class SessionQueryProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Override
    public void doProcess(BaseResult result, BaseRequest request) {
        SessionQueryRequest queryRequest = (SessionQueryRequest) request;
        SessionQueryResult queryResult = (SessionQueryResult) result;

        SessionVO sessionVO = sessionService.query(queryRequest.getSessionId());

        queryResult.setSessionVO(sessionVO);
    }
}
