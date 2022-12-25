package id.thesis.shumishumi.dalgen.service;

import id.thesis.shumishumi.dalgen.model.request.SessionDAORequest;
import id.thesis.shumishumi.dalgen.model.result.SessionDO;

public interface SesssionDAO {
    SessionDO query(SessionDAORequest request);
}
