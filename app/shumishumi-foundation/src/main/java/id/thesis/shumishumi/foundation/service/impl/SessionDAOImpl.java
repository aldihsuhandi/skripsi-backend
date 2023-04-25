package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.request.SessionDAORequest;
import id.thesis.shumishumi.foundation.model.result.SessionDO;
import id.thesis.shumishumi.foundation.repository.SessionRepository;
import id.thesis.shumishumi.foundation.service.SessionDAO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionDAOImpl implements SessionDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public SessionDO query(SessionDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("sessionDAO#query[request=%s]", request.toString()));

        String sessionId = StringUtils.defaultIfBlank(request.getSessionId(), "");

        SessionDO session = null;
        try {
            session = sessionRepository.findById(sessionId).orElse(null);
        } catch (Exception e) {
            LogUtil.exception(e.getMessage(), e);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("sessionDAO#query[result=%s])", session));
        return session;
    }

    @Override
    public void create(SessionDO session) {
        LogUtil.info(DALGEN_LOGGER, String.format("sessionDAO#create[request=%s]", session.toString()));
        try {
            sessionRepository.save(session);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void logout(SessionDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("sessionDAO#logout[request=%s]", request.toString()));
        try {
            sessionRepository.logout(request.getSessionId());
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void refreshSession(SessionDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("sessionDAO#refreshSession[request=%s]", request.toString()));
        try {
            sessionRepository.refreshSession(request.getSessionId(), request.getSessionDt());
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void deactivateExpiredSession() {
        LogUtil.info(DALGEN_LOGGER, "sessionDAO#deactivateExpiredSession");
        try {
            sessionRepository.expiredSession();
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }
}
