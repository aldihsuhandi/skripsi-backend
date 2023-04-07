package id.thesis.shumishumi.foundation.dalgen.service.impl;

import id.thesis.shumishumi.common.util.constant.DatabaseConst;
import id.thesis.shumishumi.common.util.constant.LogConstant;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.foundation.dalgen.model.mapper.RoleDOMapper;
import id.thesis.shumishumi.foundation.dalgen.model.request.RoleDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.result.RoleDO;
import id.thesis.shumishumi.foundation.dalgen.service.RoleDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleDAOImpl implements RoleDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    private static final Logger DAO_LOGGER = LoggerFactory.
            getLogger(LogConstant.DAO_LOGGER);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public RoleDO queryById(RoleDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, "request", daoRequest);
        String statement = new StatementBuilder(DatabaseConst.TABLE_ROLES, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.ROLE_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<RoleDO> roleDOS = jdbcTemplate.query(statement, ps ->
                ps.setString(1, daoRequest.getRoleId()), new RoleDOMapper());
        if (roleDOS.isEmpty()) {
            LogUtil.info(DALGEN_LOGGER, "result[]");
            return null;
        }

        LogUtil.info(DALGEN_LOGGER, "result", roleDOS.get(0));

        return roleDOS.get(0);
    }
}
