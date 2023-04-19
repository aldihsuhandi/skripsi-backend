/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.processor.session;

import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.summary.SessionSummary;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.session.SessionQueryRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.session.SessionQueryResult;
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
