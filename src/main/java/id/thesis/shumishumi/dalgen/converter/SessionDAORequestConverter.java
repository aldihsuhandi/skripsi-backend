package id.thesis.shumishumi.dalgen.converter;

import id.thesis.shumishumi.dalgen.model.request.SessionDAORequest;

import java.util.Date;

public class SessionDAORequestConverter {
    public static SessionDAORequest toDAORequest(String sessionId, Date sessionDt) {
        SessionDAORequest daoRequest = new SessionDAORequest();
        daoRequest.setSessionId(sessionId);
        daoRequest.setSessionDt(sessionDt);

        return daoRequest;
    }
}
