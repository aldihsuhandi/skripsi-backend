/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.foundation.dalgen.service.impl;

import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.common.util.constant.DatabaseConst;
import id.thesis.shumishumi.common.util.constant.LogConstant;
import id.thesis.shumishumi.foundation.dalgen.model.mapper.ClientDOMapper;
import id.thesis.shumishumi.foundation.dalgen.model.request.ClientDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.result.ClientDO;
import id.thesis.shumishumi.foundation.dalgen.service.ClientDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ClientDAOImpl.java, v 0.1 2022‐12‐26 3:35 PM Aldih Suhandi Exp $$
 */
@Service
public class ClientDAOImpl implements ClientDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    private static final Logger DAO_LOGGER = LoggerFactory.
            getLogger(LogConstant.DAO_LOGGER);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ClientDO queryById(ClientDAORequest daoRequest) {

        LogUtil.info(DALGEN_LOGGER, String.format("clientDAO#queryById[request=%s]", daoRequest));

        String statement = new StatementBuilder(DatabaseConst.TABLE_CLIENT, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.CLIENT_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<ClientDO> clientDOS = jdbcTemplate.query(statement, ps ->
                ps.setString(1, daoRequest.getClientId()), new ClientDOMapper());

        if (clientDOS.isEmpty()) {
            LogUtil.info(DALGEN_LOGGER, "clientDAO#queryById[result=null]");
            return null;
        }

        LogUtil.info(DALGEN_LOGGER, String.format("clientDAO#queryById[result=%s]", clientDOS.get(0)));

        return clientDOS.get(0);
    }
}
