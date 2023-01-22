package id.thesis.shumishumi.dalgen.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.dalgen.model.mapper.ItemImageDOMapper;
import id.thesis.shumishumi.dalgen.model.request.ItemImageDAORequest;
import id.thesis.shumishumi.dalgen.model.result.ItemImageDO;
import id.thesis.shumishumi.dalgen.service.ItemImageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemImageDAOImpl implements ItemImageDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ItemImageDO> queryByItemId(String itemId) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_IMAGES, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.ITEM_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        return jdbcTemplate.query(statement, ps -> ps.setString(1, itemId), new ItemImageDOMapper());
    }

    @Override
    public void create(ItemImageDAORequest request) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_IMAGES, DatabaseConst.STATEMENT_INSERT)
                .addValueStatement(DatabaseConst.ITEM_IMAGE_ID)
                .addValueStatement(DatabaseConst.ITEM_IMAGE)
                .addValueStatement(DatabaseConst.ITEM_ID)
                .buildStatement();

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, request.getItemImageId());
                ps.setBlob(2, request.getItemImage());
                ps.setString(3, request.getItemImageId());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void softDelete(ItemImageDAORequest request) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_IMAGES, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.IS_DELETED)
                .addSetStatement(DatabaseConst.GMT_MODIFIED)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.ITEM_IMAGE_ID, DatabaseConst.COMPARATOR_IN)
                .buildStatement();

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setBoolean(1, false);
                ps.setString(2, request.getItemImageId());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }
}
