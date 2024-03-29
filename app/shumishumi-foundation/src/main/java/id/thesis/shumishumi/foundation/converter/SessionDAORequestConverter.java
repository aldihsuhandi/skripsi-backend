package id.thesis.shumishumi.foundation.converter;

import id.thesis.shumishumi.common.model.request.session.SessionCreateInnerRequest;
import id.thesis.shumishumi.foundation.model.request.SessionDAORequest;
import id.thesis.shumishumi.foundation.model.result.SessionDO;

import java.util.Date;

public class SessionDAORequestConverter {

    public static SessionDAORequest toDAORequest(String sessionId) {
        SessionDAORequest daoRequest = new SessionDAORequest();
        daoRequest.setSessionId(sessionId);
        daoRequest.setGmtModified(new Date());

        return daoRequest;
    }

    public static SessionDO toDAORequest(SessionCreateInnerRequest innerRequest) {
        SessionDO daoRequest = new SessionDO();
        daoRequest.setSessionId(innerRequest.getSessionId());
        daoRequest.setUserId(innerRequest.getUserId());
        daoRequest.setSessionDt(innerRequest.getSessionDt());
        daoRequest.setRemembered(innerRequest.isRemembered());
        daoRequest.setActive(true);

        return daoRequest;
    }

    public static SessionDAORequest toDAORequest(String sessionId, Date sessionDt) {
        SessionDAORequest daoRequest = new SessionDAORequest();
        daoRequest.setSessionId(sessionId);
        daoRequest.setSessionDt(sessionDt);
        daoRequest.setGmtModified(new Date());

        return daoRequest;
    }

    public static SessionDAORequest toDAORequest(Date sessionDt) {
        SessionDAORequest daoRequest = new SessionDAORequest();
        daoRequest.setSessionDt(sessionDt);
        daoRequest.setGmtModified(new Date());
        daoRequest.setActive(false);

        return daoRequest;
    }
}
