/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.dalgen.service;

import id.thesis.shumishumi.dalgen.model.request.ClientDAORequest;
import id.thesis.shumishumi.dalgen.model.result.ClientDO;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ClientDAO.java, v 0.1 2022‐12‐26 3:35 PM Aldih Suhandi Exp $$
 */
public interface ClientDAO {
    ClientDO queryById(ClientDAORequest daoRequest);
}
