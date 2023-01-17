package id.thesis.shumishumi.dalgen.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.dalgen.model.mapper.CountMapper;
import id.thesis.shumishumi.dalgen.model.mapper.ItemDOMapper;
import id.thesis.shumishumi.dalgen.model.request.ItemDAORequest;
import id.thesis.shumishumi.dalgen.model.result.ItemDO;
import id.thesis.shumishumi.dalgen.service.ItemDAO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Statement;
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
    public ItemDO queryById(ItemDAORequest request) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.ITEM_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<ItemDO> itemDOS = jdbcTemplate.query(statement, ps ->
                ps.setString(1, request.getItemId()), new ItemDOMapper());

        if(itemDOS.isEmpty()) {
            return null;
        }

        return itemDOS.get(0);
    }

    @Override
    public List<ItemDO> query(ItemDAORequest request) {
        StatementBuilder builder = new StatementBuilder(DatabaseConst.TABLE_ITEM, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL);
        if (StringUtils.isNotEmpty(request.getItemName())) {
            builder = builder.addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND,
                    DatabaseConst.ITEM_NAME, DatabaseConst.COMPARATOR_LIKE);
        }

        if (StringUtils.isNotEmpty(request.getCategoryId())) {
            builder = builder.addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND,
                    DatabaseConst.CATEGORY_ID, DatabaseConst.COMPARATOR_EQUAL);
        }

        if (StringUtils.isNotEmpty(request.getHobbyId())) {
            builder = builder.addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND,
                    DatabaseConst.HOBBY_ID, DatabaseConst.COMPARATOR_EQUAL);
        }

        if (StringUtils.isNotEmpty(request.getMerchantId())) {
            builder = builder.addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND,
                    DatabaseConst.MERCHANT_ID, DatabaseConst.COMPARATOR_EQUAL);
        }

        if (StringUtils.isNotEmpty(request.getMerchantLevelId())) {
            builder = builder.addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND,
                    DatabaseConst.MERCHANT_LEVEL_ID, DatabaseConst.COMPARATOR_EQUAL);
        }

        if (request.getMinPrice() != null) {
            builder = builder.addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND,
                    DatabaseConst.ITEM_PRICE, DatabaseConst.COMPARATOR_GREATER_EQUAL);
        }

        if (request.getMaxPrice() != null) {
            builder = builder.addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND,
                    DatabaseConst.ITEM_PRICE, DatabaseConst.COMPARATOR_LESSER_EQUAL);
        }

        builder = builder.addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND,
                DatabaseConst.ITEM_QUANTITY, DatabaseConst.COMPARATOR_GREATER);

        List<ItemDO> itemDOS = jdbcTemplate.query(builder.buildStatement(), ps -> {
            int indx = 1;
            if (StringUtils.isNotEmpty(request.getItemName())) {
                ps.setString(indx++, request.getItemName());
            }

            if (StringUtils.isNotEmpty(request.getCategoryId())) {
                ps.setString(indx++, request.getCategoryId());
            }

            if (StringUtils.isNotEmpty(request.getHobbyId())) {
                ps.setString(indx++, request.getHobbyId());
            }

            if (StringUtils.isNotEmpty(request.getMerchantId())) {
                ps.setString(indx++, request.getMerchantId());
            }

            if (StringUtils.isNotEmpty(request.getMerchantLevelId())) {
                ps.setString(indx++, request.getMerchantLevelId());
            }

            if (request.getMinPrice() != null) {
                ps.setLong(indx++, request.getMinPrice());
            }

            if (request.getMaxPrice() != null) {
                ps.setLong(indx++, request.getMaxPrice());
            }

            ps.setLong(indx++, 0);
        }, new ItemDOMapper());

        return itemDOS;
    }

    @Override
    public int count() {
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_COUNT, DatabaseConst.COUNT)
                .buildStatement();

        return jdbcTemplate.query(statement, new CountMapper()).get(0);
    }

    @Override
    public void create(ItemDAORequest request) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM, DatabaseConst.ITEM_NAME)
                .addValueStatement(DatabaseConst.ITEM_ID)
                .addValueStatement(DatabaseConst.ITEM_NAME)
                .addValueStatement(DatabaseConst.ITEM_PRICE)
                .addValueStatement(DatabaseConst.ITEM_DESCRIPTION)
                .addValueStatement(DatabaseConst.ITEM_QUANTITY)
                .addValueStatement(DatabaseConst.CATEGORY_ID)
                .addValueStatement(DatabaseConst.HOBBY_ID)
                .addValueStatement(DatabaseConst.MERCHANT_ID)
                .addValueStatement(DatabaseConst.MERCHANT_LEVEL_ID)
                .buildStatement();

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, request.getItemId());
                ps.setString(2, request.getItemName());
                ps.setLong(3, request.getItemPrice());
                ps.setString(4, request.getItemDescription());
                ps.setInt(5, request.getItemQuantity());
                ps.setString(6, request.getCategoryId());
                ps.setString(7, request.getHobbyId());
                ps.setString(8, request.getMerchantId());
                ps.setString(9, request.getMerchantLevelId());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void update(ItemDAORequest request) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM, DatabaseConst.ITEM_NAME)
                .addValueStatement(DatabaseConst.ITEM_NAME)
                .addValueStatement(DatabaseConst.ITEM_PRICE)
                .addValueStatement(DatabaseConst.ITEM_DESCRIPTION)
                .addValueStatement(DatabaseConst.ITEM_QUANTITY)
                .addValueStatement(DatabaseConst.CATEGORY_ID)
                .addValueStatement(DatabaseConst.HOBBY_ID)
                .addValueStatement(DatabaseConst.MERCHANT_ID)
                .addValueStatement(DatabaseConst.MERCHANT_LEVEL_ID)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.ITEM_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, request.getItemName());
                ps.setLong(2, request.getItemPrice());
                ps.setString(3, request.getItemDescription());
                ps.setInt(4, request.getItemQuantity());
                ps.setString(5, request.getCategoryId());
                ps.setString(6, request.getHobbyId());
                ps.setString(7, request.getMerchantId());
                ps.setString(8, request.getMerchantLevelId());
                ps.setString(9, request.getItemId());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }
}
