package id.thesis.shumishumi.foundation.dalgen.service;

import id.thesis.shumishumi.foundation.dalgen.model.request.SessionDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.result.SessionDO;

public interface SessionDAO {
    SessionDO query(SessionDAORequest request);

    void create(SessionDAORequest request);

    void logout(SessionDAORequest request);

    void refreshSession(SessionDAORequest request);

    void deactivateExpiredSession(SessionDAORequest request);
}
