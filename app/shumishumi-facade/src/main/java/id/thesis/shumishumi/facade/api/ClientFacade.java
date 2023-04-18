/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.client.ClientAuthRequest;
import id.thesis.shumishumi.facade.result.client.ClientAuthResult;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ClientFacade.java, v 0.1 2022‐12‐26 3:20 PM Aldih Suhandi Exp $$
 */
public interface ClientFacade {
    ClientAuthResult authenticate(ClientAuthRequest request);
}
