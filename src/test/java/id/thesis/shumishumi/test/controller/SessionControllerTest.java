package id.thesis.shumishumi.test.controller;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.summary.SessionSummary;
import id.thesis.shumishumi.core.facade.SessionFacade;
import id.thesis.shumishumi.core.result.session.SessionLogoutResult;
import id.thesis.shumishumi.core.result.session.SessionQueryResult;
import id.thesis.shumishumi.rest.controller.SessionController;
import id.thesis.shumishumi.rest.form.session.SessionLogoutForm;
import id.thesis.shumishumi.rest.form.session.SessionQueryForm;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class SessionControllerTest extends ControllerTestBase {
    @Autowired
    private SessionController sessionController;

    @MockBean
    private SessionFacade sessionFacade;

    public void logoutTest_SUCCESS() {
        HttpHeaders headers = mockHeaders();
        SessionLogoutForm form = new SessionLogoutForm();
        form.setSessionId("sessionId");

        // mock result
        SessionLogoutResult result = new SessionLogoutResult();
        result.setResultContext(mockResultContext(ShumishumiErrorCodeEnum.SUCCESS));

        Mockito.when(sessionFacade.logout(Mockito.any())).thenReturn(result);

        ResponseEntity<SessionLogoutResult> response = sessionController.logout(headers, form);
        ResultAssert.isExpected(response.getBody().getResultContext().getResultCode(), ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }

    public void InfoTest_SUCCESS() {
        HttpHeaders headers = mockHeaders();
        SessionQueryForm form = new SessionQueryForm();
        form.setSessionId("sessionId");

        // mock result
        SessionQueryResult result = new SessionQueryResult();
        SessionSummary summary = new SessionSummary();
        summary.setEmail("email");
        summary.setRemembered(true);
        summary.setSessionDt(new Date());
        summary.setGmtCreate(new Date());
        summary.setGmtModified(new Date());
        result.setSessionSummary(summary);
        result.setResultContext(mockResultContext(ShumishumiErrorCodeEnum.SUCCESS));

        Mockito.when(sessionFacade.query(Mockito.any())).thenReturn(result);

        ResponseEntity<SessionQueryResult> response = sessionController.query(headers, form);
        ResultAssert.isExpected(response.getBody().getResultContext().getResultCode(), ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }
}
