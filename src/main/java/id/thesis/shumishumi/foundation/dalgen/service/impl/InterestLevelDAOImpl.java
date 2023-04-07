package id.thesis.shumishumi.foundation.dalgen.service.impl;

import id.thesis.shumishumi.common.util.constant.DatabaseConst;
import id.thesis.shumishumi.common.util.constant.LogConstant;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.foundation.dalgen.model.mapper.InterestLevelDOMapper;
import id.thesis.shumishumi.foundation.dalgen.model.result.InterestLevelDO;
import id.thesis.shumishumi.foundation.dalgen.service.InterestLevelDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestLevelDAOImpl implements InterestLevelDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    private static final Logger DAO_LOGGER = LoggerFactory.
            getLogger(LogConstant.DAO_LOGGER);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<InterestLevelDO> queryAll() {
        String statement = new StatementBuilder(DatabaseConst.TABLE_INTEREST_LEVEL, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<InterestLevelDO> result = jdbcTemplate.query(statement, new InterestLevelDOMapper());

        LogUtil.info(DALGEN_LOGGER, result);

        return result;
    }

    @Override
    public InterestLevelDO queryById(String interestLevelId) {
        LogUtil.info(DALGEN_LOGGER, String.format("interestLevelId=%s", interestLevelId));
        String statement = new StatementBuilder(DatabaseConst.TABLE_INTEREST_LEVEL, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.INTEREST_LEVEL_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<InterestLevelDO> interestLevelDOS = jdbcTemplate.query(statement,
                ps -> ps.setString(1, interestLevelId), new InterestLevelDOMapper());

        LogUtil.info(DAO_LOGGER, "statement", statement);

        if (interestLevelDOS.isEmpty()) {
            LogUtil.info(DALGEN_LOGGER, "[]");
            return null;
        }

        LogUtil.info(DALGEN_LOGGER, interestLevelDOS.get(0));

        return interestLevelDOS.get(0);
    }

    @Override
    public InterestLevelDO queryByName(String interestLevelName) {
        LogUtil.info(DALGEN_LOGGER, String.format("interestLevelName=%s", interestLevelName));
        String statement = new StatementBuilder(DatabaseConst.TABLE_INTEREST_LEVEL, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.INTEREST_LEVEL_NAME, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<InterestLevelDO> interestLevelDOS = jdbcTemplate.query(statement,
                ps -> ps.setString(1, interestLevelName), new InterestLevelDOMapper());

        if (interestLevelDOS.isEmpty()) {
            LogUtil.info(DALGEN_LOGGER, "[]");
            return null;
        }

        LogUtil.info(DALGEN_LOGGER, interestLevelDOS.get(0));

        return interestLevelDOS.get(0);
    }
}
