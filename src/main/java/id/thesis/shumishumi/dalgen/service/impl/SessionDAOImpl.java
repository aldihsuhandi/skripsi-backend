package id.thesis.shumishumi.dalgen.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.dalgen.model.mapper.SessionDOMapper;
import id.thesis.shumishumi.dalgen.model.request.SessionDAORequest;
import id.thesis.shumishumi.dalgen.model.result.SessionDO;
import id.thesis.shumishumi.dalgen.service.SesssionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionDAOImpl implements SesssionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public SessionDO query(SessionDAORequest request) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_SESSION, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.SESSION_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<SessionDO> sessionDOs = jdbcTemplate.query(statement, ps ->
                ps.setString(1, request.getSessionId()), new SessionDOMapper());

        if (sessionDOs == null || sessionDOs.isEmpty()) {
            return null;
        }

        return sessionDOs.get(0);
    }
}
