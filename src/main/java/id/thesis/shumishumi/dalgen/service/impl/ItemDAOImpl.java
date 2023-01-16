package id.thesis.shumishumi.dalgen.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.dalgen.model.mapper.ItemDOMapper;
import id.thesis.shumishumi.dalgen.model.request.ItemDAORequest;
import id.thesis.shumishumi.dalgen.model.result.ItemDO;
import id.thesis.shumishumi.dalgen.service.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemDAOImpl implements ItemDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ItemDO> queryAll(ItemDAORequest request) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addLimitStatement(request.getPagingContext())
                .buildStatement();

        return jdbcTemplate.query(statement, new ItemDOMapper());
    }

    @Override
    public List<ItemDO> query(ItemDAORequest request) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void create(ItemDAORequest request) {

    }

    @Override
    public void update(ItemDAORequest request) {

    }
}
