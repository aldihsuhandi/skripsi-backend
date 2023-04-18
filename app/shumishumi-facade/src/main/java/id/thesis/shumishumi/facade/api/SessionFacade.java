/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.session.SessionLogoutRequest;
import id.thesis.shumishumi.facade.request.session.SessionQueryRequest;
import id.thesis.shumishumi.facade.result.session.SessionLogoutResult;
import id.thesis.shumishumi.facade.result.session.SessionQueryResult;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: SessionFacade.java, v 0.1 2022‐12‐27 7:45 AM Aldih Suhandi Exp $$
 */
public interface SessionFacade {
    SessionLogoutResult logout(SessionLogoutRequest request);

    SessionQueryResult query(SessionQueryRequest request);
}
