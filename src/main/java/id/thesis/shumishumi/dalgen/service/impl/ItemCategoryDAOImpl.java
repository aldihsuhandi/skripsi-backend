/**
 *
 */
package id.thesis.shumishumi.dalgen.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.constant.LogConstant;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.dalgen.model.mapper.ItemCategoryDOMapper;
import id.thesis.shumishumi.dalgen.model.result.ItemCategoryDO;
import id.thesis.shumishumi.dalgen.service.ItemCategoryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ItemCategoryDAOImpl.java, v 0.1 2023‐01‐17 13:14 Aldih Suhandi Exp $$
 */
@Service
public class ItemCategoryDAOImpl implements ItemCategoryDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    private static final Logger DAO_LOGGER = LoggerFactory.
            getLogger(LogConstant.DAO_LOGGER);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(String categoryId, String categoryName) {
        LogUtil.info(DALGEN_LOGGER, String.format("categoryId=%s,categoryName=%s", categoryId, categoryName));
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_CATEGORIES, DatabaseConst.STATEMENT_INSERT)
                .addValueStatement(DatabaseConst.CATEGORY_ID)
                .addValueStatement(DatabaseConst.CATEGORY_NAME)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, categoryId);
                ps.setString(2, categoryName);
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public ItemCategoryDO queryById(String categoryId) {
        LogUtil.info(DALGEN_LOGGER, String.format("categoryId=%s", categoryId));
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_CATEGORIES, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.CATEGORY_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<ItemCategoryDO> categoryDOS = jdbcTemplate.query(statement,
                ps -> ps.setString(1, categoryId), new ItemCategoryDOMapper());

        if (categoryDOS.isEmpty()) {
            LogUtil.info(DALGEN_LOGGER, "[]");
            return null;
        }

        LogUtil.info(DALGEN_LOGGER, "result", categoryDOS.get(0));

        return categoryDOS.get(0);
    }

    @Override
    public ItemCategoryDO queryByName(String categoryName) {
        LogUtil.info(DALGEN_LOGGER, String.format("categoryName=%s", categoryName));
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_CATEGORIES, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.CATEGORY_NAME, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<ItemCategoryDO> categoryDOS = jdbcTemplate.query(statement,
                ps -> ps.setString(1, categoryName), new ItemCategoryDOMapper());

        if (categoryDOS.isEmpty()) {
            LogUtil.info(DALGEN_LOGGER, "[]");
            return null;
        }

        LogUtil.info(DALGEN_LOGGER, "result", categoryDOS.get(0));

        return categoryDOS.get(0);
    }

    @Override
    public List<ItemCategoryDO> queryAll() {
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_CATEGORIES, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .buildStatement();

        List<ItemCategoryDO> result = jdbcTemplate.query(statement, new ItemCategoryDOMapper());

        LogUtil.info(DALGEN_LOGGER, "result", result);

        return result;
    }
}
