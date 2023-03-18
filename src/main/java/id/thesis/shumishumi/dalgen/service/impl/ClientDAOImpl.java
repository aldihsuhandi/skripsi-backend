/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.dalgen.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.dalgen.model.mapper.ClientDOMapper;
import id.thesis.shumishumi.dalgen.model.request.ClientDAORequest;
import id.thesis.shumishumi.dalgen.model.result.ClientDO;
import id.thesis.shumishumi.dalgen.service.ClientDAO;
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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ClientDO queryById(ClientDAORequest daoRequest) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_CLIENT, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.CLIENT_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<ClientDO> clientDOS = jdbcTemplate.query(statement, ps ->
                ps.setString(1, daoRequest.getClientId()), new ClientDOMapper());

        if (clientDOS.isEmpty()) {
            return null;
        }

        return clientDOS.get(0);
    }
}
