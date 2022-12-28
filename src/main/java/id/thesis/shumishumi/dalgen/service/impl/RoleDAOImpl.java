package id.thesis.shumishumi.dalgen.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.dalgen.model.mapper.RoleDOMapper;
import id.thesis.shumishumi.dalgen.model.request.RoleDAORequest;
import id.thesis.shumishumi.dalgen.model.result.RoleDO;
import id.thesis.shumishumi.dalgen.service.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleDAOImpl implements RoleDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public RoleDO queryById(RoleDAORequest daoRequest) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_ROLES, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.ROLE_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<RoleDO> roleDOS = jdbcTemplate.query(statement, ps ->
                ps.setString(1, daoRequest.getRoleId()), new RoleDOMapper());
        if (roleDOS.isEmpty()) {
            return null;
        }

        return roleDOS.get(0);
    }
}
