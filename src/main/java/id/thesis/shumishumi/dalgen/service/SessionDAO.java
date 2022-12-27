package id.thesis.shumishumi.dalgen.service;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.dalgen.model.request.SessionDAORequest;
import id.thesis.shumishumi.dalgen.model.result.SessionDO;

public interface SessionDAO {
    SessionDO query(SessionDAORequest request);

    void create(SessionDAORequest request) throws ShumishumiException;

    void logout(SessionDAORequest request) throws ShumishumiException;

    void refreshSession(SessionDAORequest request) throws ShumishumiException;
}
