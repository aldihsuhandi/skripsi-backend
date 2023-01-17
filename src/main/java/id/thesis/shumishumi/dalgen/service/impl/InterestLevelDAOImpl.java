package id.thesis.shumishumi.dalgen.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.dalgen.model.mapper.InterestLevelDOMapper;
import id.thesis.shumishumi.dalgen.model.result.InterestLevelDO;
import id.thesis.shumishumi.dalgen.service.InterestLevelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestLevelDAOImpl implements InterestLevelDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<InterestLevelDO> queryAll() {
        String statement = new StatementBuilder(DatabaseConst.TABLE_INTEREST_LEVEL, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .buildStatement();

        return jdbcTemplate.query(statement, new InterestLevelDOMapper());
    }

    @Override
    public InterestLevelDO queryById(String interestLevelId) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_INTEREST_LEVEL, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.INTEREST_LEVEL_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<InterestLevelDO> interestLevelDOS = jdbcTemplate.query(statement,
                ps -> ps.setString(1, interestLevelId), new InterestLevelDOMapper());

        if (interestLevelDOS.isEmpty()) {
            return null;
        }

        return interestLevelDOS.get(0);
    }

    @Override
    public InterestLevelDO queryByName(String interestLevelName) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_INTEREST_LEVEL, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.INTEREST_LEVEL_NAME, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<InterestLevelDO> interestLevelDOS = jdbcTemplate.query(statement,
                ps -> ps.setString(1, interestLevelName), new InterestLevelDOMapper());

        if (interestLevelDOS.isEmpty()) {
            return null;
        }

        return interestLevelDOS.get(0);
    }
}
