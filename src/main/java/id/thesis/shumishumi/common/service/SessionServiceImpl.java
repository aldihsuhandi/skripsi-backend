package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.dalgen.converter.SessionDAORequestConverter;
import id.thesis.shumishumi.dalgen.model.request.SessionDAORequest;
import id.thesis.shumishumi.dalgen.service.SessionDAO;
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
        return null;
    }

    @Override
    public void refreshSession(String sessionId) throws ShumishumiException {
        Date sessionDt = Date.from(LocalDateTime.now().plus(Duration.
                of(10, ChronoUnit.MINUTES)).atZone(ZoneId.systemDefault()).toInstant());

        SessionDAORequest daoRequest = SessionDAORequestConverter.toDAORequest(sessionId, sessionDt);

        sessionDAO.refreshSession(daoRequest);
    }
}
