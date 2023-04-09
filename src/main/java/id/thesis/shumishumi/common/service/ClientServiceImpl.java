/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.common.util.converter.ViewObjectConverter;
import id.thesis.shumishumi.common.model.viewobject.ClientVO;
import id.thesis.shumishumi.core.service.ClientService;
import id.thesis.shumishumi.foundation.dalgen.converter.ClientDAORequestConverter;
import id.thesis.shumishumi.foundation.dalgen.model.request.ClientDAORequest;
import id.thesis.shumishumi.foundation.dalgen.service.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
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
