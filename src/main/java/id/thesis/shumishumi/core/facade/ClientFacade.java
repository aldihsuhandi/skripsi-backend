/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.facade;

import id.thesis.shumishumi.rest.request.client.ClientAuthRequest;
import id.thesis.shumishumi.rest.result.client.ClientAuthResult;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ClientFacade.java, v 0.1 2022‐12‐26 3:20 PM Aldih Suhandi Exp $$
 */
public interface ClientFacade {
    ClientAuthResult authenticate(ClientAuthRequest request);
}
