package id.thesis.shumishumi.core.service.session;

import id.thesis.shumishumi.common.model.viewobject.SessionVO;

public interface SessionService {
    SessionVO query(String sessionId);
}
