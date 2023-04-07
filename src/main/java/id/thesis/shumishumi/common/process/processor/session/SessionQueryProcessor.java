/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.process.processor.session;

import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.process.processor.BaseProcessor;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.session.SessionQueryRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.result.session.SessionQueryResult;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.rest.summary.SessionSummary;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: SessionQueryProcessor.java, v 0.1 2022‐12‐30 1:14 PM Aldih Suhandi Exp $$
 */
public class SessionQueryProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        SessionQueryRequest queryRequest = (SessionQueryRequest) baseRequest;
        SessionQueryResult queryResult = (SessionQueryResult) baseResult;

        SessionVO sessionVO = sessionService.query(queryRequest.getSessionId());

        UserVO userVO = userService.queryById(sessionVO.getUserId(), true);
        SessionSummary summary = new SessionSummary();
        summary.setEmail(userVO.getEmail());
        summary.setSessionDt(sessionVO.getSessionDt());
        summary.setRemembered(sessionVO.isRemembered());

        queryResult.setSessionSummary(summary);
    }
}
