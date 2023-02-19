/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.service.service;

import id.thesis.shumishumi.common.model.viewobject.ClientVO;
import id.thesis.shumishumi.core.converter.ViewObjectConverter;
import id.thesis.shumishumi.core.service.ClientService;
import id.thesis.shumishumi.foundation.converter.ClientDAORequestConverter;
import id.thesis.shumishumi.foundation.model.request.ClientDAORequest;
import id.thesis.shumishumi.foundation.service.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ClientServiceImpl.java, v 0.1 2022‐12‐26 3:31 PM Aldih Suhandi Exp $$
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDAO clientDAO;

    @Override
    public ClientVO queryById(String clientId) {
        ClientDAORequest daoRequest = ClientDAORequestConverter.toDAORequest(clientId);
        return ViewObjectConverter.toViewObject(clientDAO.queryById(daoRequest));
    }
}
