package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.request.session.SessionCreateInnerRequest;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.converter.ViewObjectConverter;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.foundation.converter.SessionDAORequestConverter;
import id.thesis.shumishumi.foundation.model.request.SessionDAORequest;
import id.thesis.shumishumi.foundation.model.result.SessionDO;
import id.thesis.shumishumi.foundation.service.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionDAO sessionDAO;

    @Override
    public SessionVO query(String sessionId) {
        SessionDAORequest daoRequest = SessionDAORequestConverter.toDAORequest(sessionId);
        SessionDO sessionDO = sessionDAO.query(daoRequest);

        return ViewObjectConverter.toViewObject(sessionDO);
    }

    @Override
    public String create(SessionCreateInnerRequest request) {
        Date sessionDt = Date.from(LocalDateTime.now().plus(Duration.
                of(10, ChronoUnit.MINUTES)).atZone(ZoneId.systemDefault()).toInstant());
        String sessionId = FunctionUtil.generateUUID();

        request.setSessionId(sessionId);
        request.setSessionDt(sessionDt);

        SessionDO sessionDO = SessionDAORequestConverter.toDAORequest(request);
        sessionDAO.create(sessionDO);

        return sessionId;
    }

    @Override
    public void refreshSession(String sessionId) {
        Date sessionDt = Date.from(LocalDateTime.now().plus(Duration.
                of(10, ChronoUnit.MINUTES)).atZone(ZoneId.systemDefault()).toInstant());

        SessionDAORequest daoRequest = SessionDAORequestConverter.toDAORequest(sessionId, sessionDt);

        sessionDAO.refreshSession(daoRequest);
    }

    @Override
    public void logout(String sessionId) {
        SessionDAORequest daoRequest = SessionDAORequestConverter.toDAORequest(sessionId);
        sessionDAO.logout(daoRequest);
    }

    @Override
    public void deactivateExpiredSession() {
        sessionDAO.deactivateExpiredSession();
    }
}
