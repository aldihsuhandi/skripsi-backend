package id.thesis.shumishumi.foundation.dalgen.service.impl;

import id.thesis.shumishumi.common.util.constant.DatabaseConst;
import id.thesis.shumishumi.common.util.constant.LogConstant;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.foundation.dalgen.model.mapper.ItemCrowdDOMapper;
import id.thesis.shumishumi.foundation.dalgen.model.mapper.UserCrowdDOMapper;
import id.thesis.shumishumi.foundation.dalgen.model.request.ItemCrowdDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.request.UserCrowdDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.result.ItemCrowdDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.UserCrowdDO;
import id.thesis.shumishumi.foundation.dalgen.service.CrowdDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrowdDAOImpl implements CrowdDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    private static final Logger DAO_LOGGER = LoggerFactory.
            getLogger(LogConstant.DAO_LOGGER);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ItemCrowdDO> queryItemCrowd(ItemCrowdDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, daoRequest);
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_CROWD, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.CROWD_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<ItemCrowdDO> result = jdbcTemplate.query(statement, ps ->
                ps.setString(1, daoRequest.getCrowdId()), new ItemCrowdDOMapper());

        LogUtil.info(DALGEN_LOGGER, result);

        return result;
    }

    @Override
    public List<UserCrowdDO> queryUserCrowd(UserCrowdDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, daoRequest);
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER_CROWD, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.USER_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<UserCrowdDO> result = jdbcTemplate.query(statement, ps ->
                ps.setString(1, daoRequest.getUserId()), new UserCrowdDOMapper());

        LogUtil.info(DALGEN_LOGGER, result);

        return result;
    }
}
