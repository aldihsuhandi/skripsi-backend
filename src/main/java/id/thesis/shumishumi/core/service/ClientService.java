/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.viewobject.ClientVO;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ClientService.java, v 0.1 2022‐12‐26 3:29 PM Aldih Suhandi Exp $$
 */
public interface ClientService {
    ClientVO queryById(String clientId);
}