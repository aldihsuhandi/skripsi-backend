package id.thesis.shumishumi.foundation.dalgen.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.constant.LogConstant;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.foundation.dalgen.model.mapper.ItemImageDOMapper;
import id.thesis.shumishumi.foundation.dalgen.model.request.ItemImageDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.result.ItemImageDO;
import id.thesis.shumishumi.foundation.dalgen.service.ItemImageDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemImageDAOImpl implements ItemImageDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    private static final Logger DAO_LOGGER = LoggerFactory.
            getLogger(LogConstant.DAO_LOGGER);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ItemImageDO> queryByItemId(String itemId) {
        LogUtil.info(DALGEN_LOGGER, "itemId", itemId);
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_IMAGES, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.ITEM_ID, DatabaseConst.COMPARATOR_EQUAL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.IS_DELETED, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();
        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<ItemImageDO> result = jdbcTemplate.query(statement, ps -> {
            ps.setString(1, itemId);
            ps.setBoolean(2, false);
        }, new ItemImageDOMapper());

        LogUtil.info(DALGEN_LOGGER, "result", result);

        return result;
    }

    @Override
    public void create(ItemImageDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, "request", request);
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_IMAGES, DatabaseConst.STATEMENT_INSERT)
                .addValueStatement(DatabaseConst.ITEM_IMAGE_ID)
                .addValueStatement(DatabaseConst.ITEM_IMAGE)
                .addValueStatement(DatabaseConst.ITEM_ID)
                .buildStatement();
        LogUtil.info(DAO_LOGGER, "statement", statement);

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
        LogUtil.info(DALGEN_LOGGER, "request", request);
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_IMAGES, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.IS_DELETED)
                .addSetStatement(DatabaseConst.GMT_MODIFIED)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.ITEM_IMAGE_ID, DatabaseConst.COMPARATOR_IN)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

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
