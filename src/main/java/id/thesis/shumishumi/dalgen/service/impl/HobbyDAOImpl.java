/**
 * 
 *
 */
package id.thesis.shumishumi.dalgen.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.dalgen.model.mapper.HobbyDOMapper;
import id.thesis.shumishumi.dalgen.model.result.HobbyDO;
import id.thesis.shumishumi.dalgen.service.HobbyDAO;
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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<HobbyDO> queryAll() {
        String statement = new StatementBuilder(DatabaseConst.TABLE_HOBBIES, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .buildStatement();

        return jdbcTemplate.query(statement, new HobbyDOMapper());
    }

    @Override
    public HobbyDO queryById(String hobbyId) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_HOBBIES, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.HOBBY_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<HobbyDO> hobbyDOS = jdbcTemplate.query(statement,
                ps -> ps.setString(1, hobbyId), new HobbyDOMapper());

        if (hobbyDOS.isEmpty()) {
            return null;
        }

        return hobbyDOS.get(0);
    }

    @Override
    public HobbyDO queryByName(String hobbyName) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_HOBBIES, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.HOBBY_NAME, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<HobbyDO> hobbyDOS = jdbcTemplate.query(statement,
                ps -> ps.setString(1, hobbyName), new HobbyDOMapper());

        if (hobbyDOS.isEmpty()) {
            return null;
        }

        return hobbyDOS.get(0);
    }

    @Override
    public void create(String hobbyId, String hobbyName) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_HOBBIES, DatabaseConst.STATEMENT_SELECT)
                .addValueStatement(DatabaseConst.HOBBY_ID)
                .addValueStatement(DatabaseConst.HOBBY_NAME)
                .buildStatement();

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
