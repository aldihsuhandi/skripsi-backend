package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.common.util.database.StatementBuilder;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.mapper.CountMapper;
import id.thesis.shumishumi.foundation.model.mapper.ItemWishlistDOMapper;
import id.thesis.shumishumi.foundation.model.result.ItemWishlistDO;
import id.thesis.shumishumi.foundation.service.ItemWishlistDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemWishlistDAOImpl implements ItemWishlistDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger DALGEN = LoggerFactory.getLogger(LogConstant.DALGEN_LOGGER);
    private static final Logger DAO = LoggerFactory.getLogger(LogConstant.DAO_LOGGER);

    @Override
    public void addWishlist(String userId, String itemId) {
        LogUtil.info(DALGEN, String.format("itemWishlistDAO#addWishlist[userId=%s,itemId=%s]", userId, itemId));
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_WISHLIST, DatabaseConst.STATEMENT_INSERT)
                .addValueStatement(DatabaseConst.USER_ID)
                .addValueStatement(DatabaseConst.ITEM_ID)
                .buildStatement();

        LogUtil.info(DAO, String.format("itemWishlistDAO#addWishlist[sql=%s]", statement));

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, userId);
                ps.setString(2, itemId);
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void removeWishlist(String userId, String itemId) {
        LogUtil.info(DALGEN, String.format("itemWishlistDAO#removeWishlist[userId=%s,itemId=%s]", userId, itemId));
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_WISHLIST, DatabaseConst.STATEMENT_DELETE)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.USER_ID, DatabaseConst.COMPARATOR_EQUAL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.ITEM_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO, String.format("itemWishlistDAO#removeWishlist[sql=%s]", statement));

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, userId);
                ps.setString(2, itemId);
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public List<ItemWishlistDO> queryUserWishlist(String userId) {
        LogUtil.info(DALGEN, String.format("itemWishlistDAO#queryUserWishlist[userId=%s]", userId));
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_WISHLIST, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.USER_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO, String.format("itemWishlistDAO#queryUserWishlist[sql=%s]", statement));

        List<ItemWishlistDO> result = jdbcTemplate.query(statement, ps ->
                ps.setString(1, userId), new ItemWishlistDOMapper());

        LogUtil.info(DALGEN, String.format("itemWishlistDAO#queryUserWishlist[result=%s]", result.toString()));

        return result;
    }

    @Override
    public int countItemWishlist(String itemId) {
        LogUtil.info(DALGEN, String.format("itemWishlistDAO#countItemWishlist[itemId=%s]", itemId));
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_WISHLIST, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_COUNT, DatabaseConst.COUNT)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.ITEM_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();
        LogUtil.info(DAO, String.format("itemWishlistDAO#countItemWishlist[sql=%s]", statement));

        Integer result = jdbcTemplate.query(statement, ps -> ps.setString(1, itemId), new CountMapper()).get(0);
        LogUtil.info(DAO, String.format("itemWishlistDAO#countItemWishlist[result=%s]", result));

        return result;
    }
}
