package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.core.facade.SessionFacade;
import id.thesis.shumishumi.dalgen.model.result.SessionDO;
import id.thesis.shumishumi.rest.request.session.SessionLogoutRequest;
import id.thesis.shumishumi.rest.result.session.SessionLogoutResult;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionFacadeTest extends TestBase {

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

    private SessionDO mockSessionDO() {
        SessionDO sessionDO = new SessionDO();
        sessionDO.setUserId("userId");
        sessionDO.setRemembered(true);
        sessionDO.setActive(true);

        return sessionDO;
    }
}
