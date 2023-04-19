package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.common.model.request.session.SessionCreateInnerRequest;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;

public interface SessionService {
    SessionVO query(String sessionId);

    String create(SessionCreateInnerRequest request);

    void refreshSession(String sessionId);

    void logout(String sessionId);

    void deactivateExpiredSession();
}
