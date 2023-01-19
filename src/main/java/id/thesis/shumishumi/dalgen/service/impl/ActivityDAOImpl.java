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
import id.thesis.shumishumi.dalgen.model.mapper.ActivityDOMapper;
import id.thesis.shumishumi.dalgen.model.mapper.UserActivityDOMapper;
import id.thesis.shumishumi.dalgen.model.request.UserActivityDAORequest;
import id.thesis.shumishumi.dalgen.model.result.ActivityDO;
import id.thesis.shumishumi.dalgen.model.result.UserActivityDO;
import id.thesis.shumishumi.dalgen.service.ActivityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ActivityDAOImpl.java, v 0.1 2023‐01‐19 9:27 AM Aldih Suhandi Exp $$
 */
@Service
public class ActivityDAOImpl implements ActivityDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertUserActivity(UserActivityDAORequest request) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER_ACTIVITY, DatabaseConst.STATEMENT_INSERT)
                .addValueStatement(DatabaseConst.USER_ACTIVITY_ID)
                .addValueStatement(DatabaseConst.USER_ID)
                .addValueStatement(DatabaseConst.ITEM_ID)
                .addValueStatement(DatabaseConst.ACTIVITY_ID)
                .buildStatement();

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, request.getActivitytId());
                ps.setString(2, request.getUserId());
                ps.setString(3, request.getItemId());
                ps.setString(4, request.getActivitytId());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public List<UserActivityDO> queryUserActivity(String userId) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER_ACTIVITY, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.USER_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        return jdbcTemplate.query(statement, ps ->
                ps.setString(1, userId), new UserActivityDOMapper());
    }

    @Override
    public ActivityDO queryActivityById(String activityId) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_ACTIVITY, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.ACTIVITY_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<ActivityDO> activityDOS = jdbcTemplate.query(statement, ps ->
                ps.setString(1, activityId), new ActivityDOMapper());
        if (activityDOS.isEmpty()) {
            return null;
        }

        return activityDOS.get(0);
    }

    @Override
    public ActivityDO queryActivityByName(String activityName) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_ACTIVITY, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.ACTIVITY_NAME, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<ActivityDO> activityDOS = jdbcTemplate.query(statement, ps ->
                ps.setString(1, activityName), new ActivityDOMapper());
        if (activityDOS.isEmpty()) {
            return null;
        }

        return activityDOS.get(0);
    }
}
