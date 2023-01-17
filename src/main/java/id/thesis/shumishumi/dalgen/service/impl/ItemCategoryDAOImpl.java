/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.dalgen.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.dalgen.model.mapper.ItemCategoryDOMapper;
import id.thesis.shumishumi.dalgen.model.result.ItemCategoryDO;
import id.thesis.shumishumi.dalgen.service.ItemCategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ItemCategoryDAOImpl.java, v 0.1 2023‐01‐17 13:14 Aldih Suhandi Exp $$
 */
@Service
public class ItemCategoryDAOImpl implements ItemCategoryDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(String categoryId, String categoryName) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_CATEGORIES, DatabaseConst.STATEMENT_INSERT)
                .addValueStatement(DatabaseConst.CATEGORY_ID)
                .addValueStatement(DatabaseConst.CATEGORY_NAME)
                .buildStatement();

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
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_CATEGORIES, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.CATEGORY_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<ItemCategoryDO> categoryDOS = jdbcTemplate.query(statement,
                ps -> ps.setString(1, categoryId), new ItemCategoryDOMapper());

        if (categoryDOS.isEmpty()) {
            return null;
        }

        return categoryDOS.get(0);
    }

    @Override
    public ItemCategoryDO queryByName(String categoryName) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_CATEGORIES, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.CATEGORY_NAME, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<ItemCategoryDO> categoryDOS = jdbcTemplate.query(statement,
                ps -> ps.setString(1, categoryName), new ItemCategoryDOMapper());

        if (categoryDOS.isEmpty()) {
            return null;
        }

        return categoryDOS.get(0);
    }

    @Override
    public List<ItemCategoryDO> queryAll() {
        String statement = new StatementBuilder(DatabaseConst.TABLE_ITEM_CATEGORIES, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .buildStatement();

        return jdbcTemplate.query(statement, new ItemCategoryDOMapper());
    }
}
