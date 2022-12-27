package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.request.session.SessionCreateInnerRequest;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;

public interface SessionService {
    SessionVO query(String sessionId);

    String create(SessionCreateInnerRequest request) throws ShumishumiException;

    void refreshSession(String sessionId) throws ShumishumiException;

    void logout(String sessionId) throws ShumishumiException;
}
