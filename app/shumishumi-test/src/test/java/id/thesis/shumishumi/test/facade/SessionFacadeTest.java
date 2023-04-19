package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.facade.api.SessionFacade;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.session.SessionLogoutRequest;
import id.thesis.shumishumi.facade.request.session.SessionQueryRequest;
import id.thesis.shumishumi.facade.result.session.SessionLogoutResult;
import id.thesis.shumishumi.facade.result.session.SessionQueryResult;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionFacadeTest extends FacadeTestBase {

    @Autowired
    private SessionFacade sessionFacade;

    @Test
    public void sessionLogoutTest_SUCCESS() {
        SessionLogoutRequest request = new SessionLogoutRequest();
        request.setSessionId("sessionId");

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO());

        SessionLogoutResult result = sessionFacade.logout(request);

        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }

    @Test
    public void sessionLogoutTest_FAILED_expired() {
        SessionLogoutRequest request = new SessionLogoutRequest();
        request.setSessionId("sessionId");


        SessionLogoutResult result = sessionFacade.logout(request);

        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.SESSION_EXPIRED.getErrorCode());
    }

    @Test
    public void sessionQueryTest_SUCCESS() {
        SessionQueryRequest request = new SessionQueryRequest();
        request.setSessionId("sessionId");

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO());
        Mockito.when(userDAO.queryById(Mockito.any())).thenReturn(mockUserDO("password"));

        SessionQueryResult result = sessionFacade.query(request);

        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }
}
