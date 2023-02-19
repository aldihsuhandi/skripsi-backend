package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.request.SessionDAORequest;
import id.thesis.shumishumi.foundation.model.result.SessionDO;

public interface SessionDAO {
    SessionDO query(SessionDAORequest request);

    void create(SessionDAORequest request);

    void logout(SessionDAORequest request);

    void refreshSession(SessionDAORequest request);

    void deactivateExpiredSession(SessionDAORequest request);
}
