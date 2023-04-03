/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.foundation.dalgen.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.constant.LogConstant;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.enumeration.UserRolesEnum;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.foundation.dalgen.model.mapper.UserDOMapper;
import id.thesis.shumishumi.foundation.dalgen.model.request.UserDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.result.UserDO;
import id.thesis.shumishumi.foundation.dalgen.service.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserDAOImpl.java, v 0.1 2022‐12‐26 7:30 AM Aldih Suhandi Exp $$
 */
@Service
public class UserDAOImpl implements UserDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    private static final Logger DAO_LOGGER = LoggerFactory.
            getLogger(LogConstant.DAO_LOGGER);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(UserDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("request=%s", daoRequest.toString()));
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER, DatabaseConst.STATEMENT_INSERT)
                .addValueStatement(DatabaseConst.USER_ID)
                .addValueStatement(DatabaseConst.EMAIL)
                .addValueStatement(DatabaseConst.PHONE_NUMBER)
                .addValueStatement(DatabaseConst.USERNAME)
                .addValueStatement(DatabaseConst.ROLE_ID)
                .addValueStatement(DatabaseConst.PASSWORD)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);


        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, daoRequest.getUserId());
                ps.setString(2, daoRequest.getEmail());
                ps.setString(3, daoRequest.getPhoneNumber());
                ps.setString(4, daoRequest.getUsername());
                ps.setString(5, UserRolesEnum.USER.getUserRoleId());
                ps.setString(6, daoRequest.getPassword());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void update(UserDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("request=%s", daoRequest.toString()));
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.EMAIL)
                .addSetStatement(DatabaseConst.USERNAME)
                .addSetStatement(DatabaseConst.PHONE_NUMBER)
                .addSetStatement(DatabaseConst.PROFILE_PICTURE)
                .addSetStatement(DatabaseConst.PASSWORD)
                .addSetStatement(DatabaseConst.IS_ACTIVE)
                .addSetStatement(DatabaseConst.IS_DELETED)
                .addSetStatement(DatabaseConst.GMT_MODIFIED)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.USER_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, daoRequest.getEmail());
                ps.setString(2, daoRequest.getUsername());
                ps.setString(3, daoRequest.getPhoneNumber());
                ps.setBlob(4, daoRequest.getProfilePicture());
                ps.setString(5, daoRequest.getPassword());
                ps.setBoolean(6, daoRequest.isActive());
                ps.setBoolean(7, daoRequest.isDeleted());
                ps.setTimestamp(8, new Timestamp(daoRequest.getGmtModified().getTime()));
                ps.setString(9, daoRequest.getUserId());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void updateProfilePicture(UserDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("request=%s", daoRequest.toString()));
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.PROFILE_PICTURE)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.USER_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setBlob(1, daoRequest.getProfilePicture());
                ps.setString(2, daoRequest.getUserId());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void changeRole(UserDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("request=%s", daoRequest.toString()));
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.ROLE_ID)
                .addSetStatement(DatabaseConst.GMT_MODIFIED)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.USER_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();
        LogUtil.info(DAO_LOGGER, "statement", statement);

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, daoRequest.getRoleId());
                ps.setTimestamp(2, new Timestamp(daoRequest.getGmtModified().getTime()));
                ps.setString(3, daoRequest.getUserId());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public UserDO queryById(UserDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("request=%s", daoRequest.toString()));
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.USER_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<UserDO> userDOS = jdbcTemplate.query(statement, ps ->
                ps.setString(1, daoRequest.getUserId()), new UserDOMapper());

        if (userDOS.isEmpty()) {
            LogUtil.info(DALGEN_LOGGER, "result[]");
            return null;
        }

        LogUtil.info(DALGEN_LOGGER, "result", userDOS.get(0));
        return userDOS.get(0);
    }

    @Override
    public List<UserDO> queryByIds(List<UserDAORequest> userDAORequests) {
        LogUtil.info(DALGEN_LOGGER, String.format("request=%s", userDAORequests.toString()));
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.USER_ID, DatabaseConst.COMPARATOR_IN)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        String userIds = userDAORequests.stream().map(UserDAORequest::getUserId).collect(Collectors.joining(", "));
        List<UserDO> userDOS = jdbcTemplate.query(statement, ps -> ps.setString(1, userIds), new UserDOMapper());

        LogUtil.info(DALGEN_LOGGER, String.format("result=%s", userDOS));

        return userDOS;
    }

    @Override
    public UserDO queryByEmail(UserDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("request=%s", daoRequest.toString()));
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.EMAIL, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<UserDO> userDOS = jdbcTemplate.query(statement, ps ->
                ps.setString(1, daoRequest.getEmail()), new UserDOMapper());

        if (userDOS.isEmpty()) {
            LogUtil.info(DALGEN_LOGGER, "result[]");
            return null;
        }

        LogUtil.info(DALGEN_LOGGER, "result", userDOS.get(0));
        return userDOS.get(0);
    }

    @Override
    public UserDO queryByPhoneNumber(UserDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("request=%s", daoRequest.toString()));
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.PHONE_NUMBER, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<UserDO> userDOS = jdbcTemplate.query(statement, ps ->
                ps.setString(1, daoRequest.getPhoneNumber()), new UserDOMapper());

        if (userDOS.isEmpty()) {
            LogUtil.info(DALGEN_LOGGER, "result[]");
            return null;
        }

        LogUtil.info(DALGEN_LOGGER, "result", userDOS.get(0));
        return userDOS.get(0);
    }

    @Override
    public List<UserDO> queryAll() {
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<UserDO> result = jdbcTemplate.query(statement, new UserDOMapper());
        LogUtil.info(DALGEN_LOGGER, "result", result);

        return result;
    }
}
