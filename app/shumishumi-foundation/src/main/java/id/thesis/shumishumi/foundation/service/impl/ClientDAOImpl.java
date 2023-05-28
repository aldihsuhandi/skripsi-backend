/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.foundation.model.request.ClientDAORequest;
import id.thesis.shumishumi.foundation.model.result.ClientDO;
import id.thesis.shumishumi.foundation.repository.ClientRepository;
import id.thesis.shumishumi.foundation.service.ClientDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ClientDAOImpl.java, v 0.1 2022‐12‐26 3:35 PM Aldih Suhandi Exp $$
 */
@Service
public class ClientDAOImpl implements ClientDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDO queryById(ClientDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("clientDAO#queryById[request=%s]", daoRequest));

        ClientDO clientDO = clientRepository.findById(daoRequest.getClientId()).orElse(null);

        LogUtil.info(DALGEN_LOGGER, String.format("clientDAO#queryById[result=%s]", clientDO));
        return clientDO;
    }
}
