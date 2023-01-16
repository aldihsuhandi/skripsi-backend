package id.thesis.shumishumi.dalgen.service;

import id.thesis.shumishumi.dalgen.model.request.SessionDAORequest;
import id.thesis.shumishumi.dalgen.model.result.SessionDO;

public interface SessionDAO {
    SessionDO query(SessionDAORequest request);

    void create(SessionDAORequest request);

    void logout(SessionDAORequest request);

    void refreshSession(SessionDAORequest request);

    void deactivateExpiredSession(SessionDAORequest request);
}
