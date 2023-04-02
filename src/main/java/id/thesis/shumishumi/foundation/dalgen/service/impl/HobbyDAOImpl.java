/**
 *
 */
package id.thesis.shumishumi.foundation.dalgen.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.constant.LogConstant;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.foundation.dalgen.model.mapper.HobbyDOMapper;
import id.thesis.shumishumi.foundation.dalgen.model.result.HobbyDO;
import id.thesis.shumishumi.foundation.dalgen.service.HobbyDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: HobbyDAOImpl.java, v 0.1 2023‐01‐17 13:51 Aldih Suhandi Exp $$
 */
@Service
public class HobbyDAOImpl implements HobbyDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    private static final Logger DAO_LOGGER = LoggerFactory.
            getLogger(LogConstant.DAO_LOGGER);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<HobbyDO> queryAll() {
        String statement = new StatementBuilder(DatabaseConst.TABLE_HOBBIES, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<HobbyDO> result = jdbcTemplate.query(statement, new HobbyDOMapper());

        LogUtil.info(DALGEN_LOGGER, result);

        return result;
    }

    @Override
    public HobbyDO queryById(String hobbyId) {

        LogUtil.info(DALGEN_LOGGER, hobbyId);

        String statement = new StatementBuilder(DatabaseConst.TABLE_HOBBIES, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.HOBBY_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<HobbyDO> hobbyDOS = jdbcTemplate.query(statement,
                ps -> ps.setString(1, hobbyId), new HobbyDOMapper());

        if (hobbyDOS.isEmpty()) {
            return null;
        }

        LogUtil.info(DALGEN_LOGGER, hobbyDOS.get(0));

        return hobbyDOS.get(0);
    }

    @Override
    public HobbyDO queryByName(String hobbyName) {

        LogUtil.info(DALGEN_LOGGER, String.format("hobbyName=%s", hobbyName));

        String statement = new StatementBuilder(DatabaseConst.TABLE_HOBBIES, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.HOBBY_NAME, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<HobbyDO> hobbyDOS = jdbcTemplate.query(statement,
                ps -> ps.setString(1, hobbyName), new HobbyDOMapper());

        if (hobbyDOS.isEmpty()) {
            return null;
        }

        LogUtil.info(DALGEN_LOGGER, hobbyDOS.get(0));

        return hobbyDOS.get(0);
    }

    @Override
    public void create(String hobbyId, String hobbyName) {

        LogUtil.info(DALGEN_LOGGER, String.format("hobbyId=%s,hobbyName=%s", hobbyId, hobbyName));

        String statement = new StatementBuilder(DatabaseConst.TABLE_HOBBIES, DatabaseConst.STATEMENT_INSERT)
                .addValueStatement(DatabaseConst.HOBBY_ID)
                .addValueStatement(DatabaseConst.HOBBY_NAME)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, hobbyId);
                ps.setString(2, hobbyName);
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }
}
