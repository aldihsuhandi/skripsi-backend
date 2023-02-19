/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.request.ClientDAORequest;
import id.thesis.shumishumi.foundation.model.result.ClientDO;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ClientDAO.java, v 0.1 2022‐12‐26 3:35 PM Aldih Suhandi Exp $$
 */
public interface ClientDAO {
    ClientDO queryById(ClientDAORequest daoRequest);
}
