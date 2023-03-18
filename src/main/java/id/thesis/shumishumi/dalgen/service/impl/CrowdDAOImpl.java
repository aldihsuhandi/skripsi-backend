package id.thesis.shumishumi.dalgen.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.dalgen.model.mapper.ItemCrowdDOMapper;
import id.thesis.shumishumi.dalgen.model.mapper.UserCrowdDOMapper;
import id.thesis.shumishumi.dalgen.model.request.ItemCrowdDAORequest;
import id.thesis.shumishumi.dalgen.model.request.UserCrowdDAORequest;
import id.thesis.shumishumi.dalgen.model.result.ItemCrowdDO;
import id.thesis.shumishumi.dalgen.model.result.UserCrowdDO;
import id.thesis.shumishumi.dalgen.service.CrowdDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrowdDAOImpl implements CrowdDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ItemCrowdDO> queryItemCrowd(ItemCrowdDAORequest daoRequest) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_CROWD, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.CROWD_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        return jdbcTemplate.query(statement, ps ->
                ps.setString(1, daoRequest.getCrowdId()), new ItemCrowdDOMapper());
    }

    @Override
    public List<UserCrowdDO> queryUserCrowd(UserCrowdDAORequest daoRequest) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER_CROWD, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.USER_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();
        return jdbcTemplate.query(statement, ps ->
                ps.setString(1, daoRequest.getUserId()), new UserCrowdDOMapper());
    }
}
