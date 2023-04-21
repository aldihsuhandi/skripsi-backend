package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.request.SessionDAORequest;
import id.thesis.shumishumi.foundation.model.result.SessionDO;

public interface SessionDAO {
    SessionDO query(SessionDAORequest request);

    void create(SessionDO session);

    void logout(SessionDAORequest request);

    void refreshSession(SessionDAORequest request);

    void deactivateExpiredSession();
}
