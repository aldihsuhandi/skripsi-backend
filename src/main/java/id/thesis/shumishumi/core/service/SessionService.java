package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;

public interface SessionService {
    SessionVO query(String sessionId);

    void refreshSession(String sessionId) throws ShumishumiException;
}
