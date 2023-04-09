/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.rest.controller;

import id.thesis.shumishumi.common.process.callback.ControllerCallback;
import id.thesis.shumishumi.common.process.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.core.facade.SessionFacade;
import id.thesis.shumishumi.core.request.session.SessionLogoutRequest;
import id.thesis.shumishumi.core.request.session.SessionQueryRequest;
import id.thesis.shumishumi.core.result.session.SessionLogoutResult;
import id.thesis.shumishumi.core.result.session.SessionQueryResult;
import id.thesis.shumishumi.rest.form.session.SessionLogoutForm;
import id.thesis.shumishumi.rest.form.session.SessionQueryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: SessionController.java, v 0.1 2022‐12‐27 7:41 AM Aldih Suhandi Exp $$
 */
@RestController
@RequestMapping("/session")
public class SessionController extends BaseController {

    @Autowired
    private SessionFacade sessionFacade;

    @PostMapping("/logout")
    public ResponseEntity<SessionLogoutResult> logout(@RequestHeader HttpHeaders headers, @RequestBody SessionLogoutForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<SessionLogoutResult, SessionLogoutRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public SessionLogoutRequest composeRequest() {
                SessionLogoutRequest request = new SessionLogoutRequest();
                request.setSessionId(form.getSessionId());

                return request;
            }

            @Override
            public SessionLogoutResult doProcess(SessionLogoutRequest request) {
                return sessionFacade.logout(request);
            }
        });
    }

    @PostMapping("/info")
    public ResponseEntity<SessionQueryResult> query(@RequestHeader HttpHeaders headers, @RequestBody SessionQueryForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<SessionQueryResult, SessionQueryRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public SessionQueryRequest composeRequest() {
                SessionQueryRequest request = new SessionQueryRequest();
                request.setSessionId(form.getSessionId());

                return request;
            }

            @Override
            public SessionQueryResult doProcess(SessionQueryRequest request) {
                return sessionFacade.query(request);
            }
        });
    }
}
