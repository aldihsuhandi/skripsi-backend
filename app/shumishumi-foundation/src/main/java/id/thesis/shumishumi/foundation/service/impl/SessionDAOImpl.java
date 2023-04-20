package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.common.util.database.StatementBuilder;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.request.SessionDAORequest;
import id.thesis.shumishumi.foundation.model.result.SessionDO;
import id.thesis.shumishumi.foundation.repository.SessionRepository;
import id.thesis.shumishumi.foundation.service.SessionDAO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class SessionDAOImpl implements SessionDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    private static final Logger DAO_LOGGER = LoggerFactory.
            getLogger(LogConstant.DAO_LOGGER);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public SessionDO query(SessionDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("sessionDAO#query[request=%s]", request.toString()));

        String sessionId = StringUtils.defaultIfBlank(request.getSessionId(), "");

        SessionDO session = null;
        try {
            session = sessionRepository.findById(sessionId).orElse(null);
        } catch (Exception e) {
            LogUtil.exception(e.getMessage(), e);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("sessionDAO#query[result=%s])", session));
        return session;
    }

    @Override
    public void create(SessionDO session) {
        LogUtil.info(DALGEN_LOGGER, String.format("sessionDAO#create[request=%s]", session.toString()));
        try {
            sessionRepository.save(session);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void logout(SessionDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("sessionDAO#logout[request=%s]", request.toString()));
        String statement = new StatementBuilder(DatabaseConst.TABLE_SESSION, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.IS_ACTIVE)
                .addSetStatement(DatabaseConst.GMT_MODIFIED)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.SESSION_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

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
        LogUtil.info(DALGEN_LOGGER, String.format("sessionDAO#refreshSession[request=%s]", request.toString()));
        String statement = new StatementBuilder(DatabaseConst.TABLE_SESSION, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.SESSION_DT)
                .addSetStatement(DatabaseConst.GMT_MODIFIED)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.SESSION_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

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

    @Override
    public void deactivateExpiredSession(SessionDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("sessionDAO#deactivateExpiredSession[request=%s]", request.toString()));
        String statement = new StatementBuilder(DatabaseConst.TABLE_SESSION, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.IS_ACTIVE)
                .addSetStatement(DatabaseConst.GMT_MODIFIED)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.SESSION_DT, DatabaseConst.COMPARATOR_LESSER_EQUAL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.IS_ACTIVE, DatabaseConst.COMPARATOR_EQUAL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.IS_REMEMBERED, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        try {
            jdbcTemplate.update(statement, ps -> {
                ps.setBoolean(1, request.isActive());
                ps.setTimestamp(2, new Timestamp(request.getGmtModified().getTime()));
                ps.setTimestamp(3, new Timestamp(request.getSessionDt().getTime()));
                ps.setBoolean(4, true);
                ps.setBoolean(5, false);
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }
}
