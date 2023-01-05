/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.rest.controller;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.core.facade.SessionFacade;
import id.thesis.shumishumi.process.callback.ControllerCallback;
import id.thesis.shumishumi.process.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.rest.request.HtmlRequest;
import id.thesis.shumishumi.rest.request.session.SessionLogoutRequest;
import id.thesis.shumishumi.rest.request.session.SessionQueryRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import id.thesis.shumishumi.rest.result.session.SessionLogoutResult;
import id.thesis.shumishumi.rest.result.session.SessionQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: SessionController.java, v 0.1 2022‐12‐27 7:41 AM Aldih Suhandi Exp $$
 */
@RestController
@RequestMapping("/session")
public class SessionController extends BaseController {

    @Autowired
    private SessionFacade sessionFacade;

    @PostMapping("/logout")
    public SessionLogoutResult logout(@RequestBody HtmlRequest<SessionLogoutRequest> request) {
        return (SessionLogoutResult) ControllerCallbackSupport.process(request.getHead(), request.getBody(), new ControllerCallback() {
            @Override
            public void authCheck() throws ShumishumiException {
                authenticate(request.getHead());
            }

            @Override
            public BaseResult initResult() {
                return new SessionLogoutResult();
            }

            @Override
            public BaseResult doProcess() {
                return sessionFacade.logout(request.getBody());
            }
        });
    }

    @PostMapping("/info")
    public SessionQueryResult query(@RequestBody HtmlRequest<SessionQueryRequest> request) {
        return (SessionQueryResult) ControllerCallbackSupport.process(request.getHead(), request.getBody(), new ControllerCallback() {
            @Override
            public void authCheck() throws ShumishumiException {
                authenticate(request.getHead());
            }

            @Override
            public BaseResult initResult() {
                return new SessionQueryResult();
            }

            @Override
            public BaseResult doProcess() {
                return null;
            }
        });
    }
}
