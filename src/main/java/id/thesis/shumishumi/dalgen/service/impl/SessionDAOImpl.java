package id.thesis.shumishumi.dalgen.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.dalgen.model.mapper.SessionDOMapper;
import id.thesis.shumishumi.dalgen.model.request.SessionDAORequest;
import id.thesis.shumishumi.dalgen.model.result.SessionDO;
import id.thesis.shumishumi.dalgen.service.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SessionDAOImpl implements SessionDAO {

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

        if (sessionDOs.isEmpty()) {
            return null;
        }

        return sessionDOs.get(0);
    }

    @Override
    public void create(SessionDAORequest request) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_SESSION, DatabaseConst.STATEMENT_INSERT)
                .addValueStatement(DatabaseConst.SESSION_ID)
                .addValueStatement(DatabaseConst.USER_ID)
                .addValueStatement(DatabaseConst.SESSION_DT)
                .addValueStatement(DatabaseConst.IS_ACTIVE)
                .addValueStatement(DatabaseConst.IS_REMEMBERED)
                .buildStatement();

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, request.getSessionId());
                ps.setString(2, request.getUserId());
                ps.setTimestamp(3, new Timestamp(request.getSessionDt().getTime()));
                ps.setBoolean(4, request.isActive());
                ps.setBoolean(5, request.isRemembered());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void logout(SessionDAORequest request) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_SESSION, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.IS_ACTIVE)
                .addSetStatement(DatabaseConst.GMT_MODIFIED)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.SESSION_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setBoolean(1, false);
                ps.setTimestamp(2, new Timestamp(request.getGmtModified().getTime()));
                ps.setString(3, request.getSessionId());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void refreshSession(SessionDAORequest request) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_SESSION, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.SESSION_DT)
                .addSetStatement(DatabaseConst.GMT_MODIFIED)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.SESSION_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setTimestamp(1, new Timestamp(request.getSessionDt().getTime()));
                ps.setTimestamp(2, new Timestamp(request.getGmtModified().getTime()));
                ps.setString(3, request.getSessionId());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }
}
