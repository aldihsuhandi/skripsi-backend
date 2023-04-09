package id.thesis.shumishumi.foundation.dalgen.service.impl;

import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.context.PagingContext;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.common.util.constant.DatabaseConst;
import id.thesis.shumishumi.common.util.constant.LogConstant;
import id.thesis.shumishumi.foundation.dalgen.model.mapper.CountMapper;
import id.thesis.shumishumi.foundation.dalgen.model.mapper.ItemDOMapper;
import id.thesis.shumishumi.foundation.dalgen.model.request.ItemDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.result.ItemDO;
import id.thesis.shumishumi.foundation.dalgen.service.ItemDAO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemDAOImpl implements ItemDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    private static final Logger DAO_LOGGER = LoggerFactory.
            getLogger(LogConstant.DAO_LOGGER);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ItemDO> queryAll(ItemDAORequest request) {

        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#queryAll[request=%s]", request.toString()));

        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addLimitStatement(request.getPagingContext())
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<ItemDO> result = jdbcTemplate.query(statement, new ItemDOMapper());

        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#queryAll[result=%s]", result));

        return result;
    }

    @Override
    public ItemDO queryById(ItemDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#queryById[request=%s]", request.toString()));

        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.ITEM_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<ItemDO> itemDOS = jdbcTemplate.query(statement, ps ->
                ps.setString(1, request.getItemId()), new ItemDOMapper());

        LogUtil.info(DAO_LOGGER, "statement", statement);

        if (itemDOS.isEmpty()) {
            LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#queryById[result=[]]"));
            return null;
        }

        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#queryById[result=%s]", itemDOS.get(0).toString()));

        return itemDOS.get(0);
    }

    @Override
    public List<ItemDO> query(ItemDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#query[request=%s]", request.toString()));

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

        if (StringUtils.isNotEmpty(request.getUserLevelId())) {
            builder = builder.addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND,
                    DatabaseConst.USER_LEVEL_ID, DatabaseConst.COMPARATOR_EQUAL);
        }

        if (request.getMinPrice() != null) {
            builder = builder.addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND,
                    DatabaseConst.ITEM_PRICE, DatabaseConst.COMPARATOR_GREATER_EQUAL);
        }

        if (request.getMaxPrice() != null) {
            builder = builder.addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND,
                    DatabaseConst.ITEM_PRICE, DatabaseConst.COMPARATOR_LESSER_EQUAL);
        }

        builder = builder.addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.IS_DELETED,
                DatabaseConst.COMPARATOR_EQUAL);
        builder = builder.addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.IS_APPROVED,
                DatabaseConst.COMPARATOR_EQUAL);

        builder = builder.addLimitStatement(request.getPagingContext());

        String statement = builder.buildStatement();
        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<ItemDO> result = jdbcTemplate.query(statement, ps -> {
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

            if (StringUtils.isNotEmpty(request.getUserLevelId())) {
                ps.setString(indx++, request.getUserLevelId());
            }

            if (request.getMinPrice() != null) {
                ps.setLong(indx++, request.getMinPrice());
            }

            if (request.getMaxPrice() != null) {
                ps.setLong(indx++, request.getMaxPrice());
            }

            ps.setBoolean(indx++, request.isDeleted());
            ps.setBoolean(indx++, request.isApproved());
        }, new ItemDOMapper());

        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#query[result=%s]", result));

        return result;
    }

    @Override
    public int count() {
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_COUNT, DatabaseConst.COUNT)
                .buildStatement();
        LogUtil.info(DAO_LOGGER, "statement", statement);

        int result = jdbcTemplate.query(statement, new CountMapper()).get(0);

        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#count[result=%d]", result));

        return result;
    }

    @Override
    public void create(ItemDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#create[request=%s]", request.toString()));
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM, DatabaseConst.STATEMENT_INSERT)
                .addValueStatement(DatabaseConst.ITEM_ID)
                .addValueStatement(DatabaseConst.ITEM_NAME)
                .addValueStatement(DatabaseConst.ITEM_PRICE)
                .addValueStatement(DatabaseConst.ITEM_DESCRIPTION)
                .addValueStatement(DatabaseConst.ITEM_QUANTITY)
                .addValueStatement(DatabaseConst.CATEGORY_ID)
                .addValueStatement(DatabaseConst.HOBBY_ID)
                .addValueStatement(DatabaseConst.MERCHANT_ID)
                .addValueStatement(DatabaseConst.MERCHANT_LEVEL_ID)
                .addValueStatement(DatabaseConst.IS_APPROVED)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

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
                ps.setBoolean(10, request.isApproved());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void update(ItemDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#update[request=%s]", request.toString()));
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.ITEM_NAME)
                .addSetStatement(DatabaseConst.ITEM_PRICE)
                .addSetStatement(DatabaseConst.ITEM_DESCRIPTION)
                .addSetStatement(DatabaseConst.ITEM_QUANTITY)
                .addSetStatement(DatabaseConst.CATEGORY_ID)
                .addSetStatement(DatabaseConst.HOBBY_ID)
                .addSetStatement(DatabaseConst.MERCHANT_LEVEL_ID)
                .addSetStatement(DatabaseConst.GMT_MODIFIED)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.ITEM_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, request.getItemName());
                ps.setLong(2, request.getItemPrice());
                ps.setString(3, request.getItemDescription());
                ps.setInt(4, request.getItemQuantity());
                ps.setString(5, request.getCategoryId());
                ps.setString(6, request.getHobbyId());
                ps.setString(7, request.getMerchantLevelId());
                ps.setTimestamp(8, new Timestamp(request.getGmtModified().getTime()));
                ps.setString(9, request.getItemId());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void updateImage(ItemDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#updateImage[request=%s]", request));
        String statement = new StatementBuilder(DatabaseConst.TABLE_IMAGE, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.ITEM_IMAGE)
                .addSetStatement(DatabaseConst.GMT_MODIFIED)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.ITEM_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();
        LogUtil.info(DAO_LOGGER, "statement", statement);

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, request.getItemImages());
                ps.setTimestamp(2, new Timestamp(new Date().getTime()));
                ps.setString(3, request.getItemId());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void approve(ItemDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#approve[request=%s]", request.toString()));
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.IS_APPROVED)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.ITEM_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();
        LogUtil.info(DAO_LOGGER, "statement", statement);

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setBoolean(1, true);
                ps.setString(2, request.getItemId());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public List<String> autocomplete(ItemDAORequest request) {
        PagingContext pagingContext = new PagingContext();
        pagingContext.setPageNumber(1);
        pagingContext.setNumberOfItem(15);

        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#autocomplete[request=%s,pagingContext=%s]", request.toString(), pagingContext.toString()));
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.ITEM_NAME)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.ITEM_NAME, DatabaseConst.COMPARATOR_LIKE)
                .addLimitStatement(pagingContext)
                .buildStatement();
        LogUtil.info(DAO_LOGGER, "statement", statement);

        String itemName = "%" + StringUtils.lowerCase(request.getItemName()) + "%";

        List<String> result = jdbcTemplate.query(statement, ps
                        -> ps.setString(1, itemName), new ItemDOMapper())
                .stream().map(ItemDO::getItemName).collect(Collectors.toList());

        LogUtil.info(DALGEN_LOGGER, String.format("itemDAO#autocomplete[result=%s]", result));

        return result;
    }
}
