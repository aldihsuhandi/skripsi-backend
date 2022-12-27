/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.dalgen.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.enumeration.UserRolesEnum;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.dalgen.model.mapper.UserDOMapper;
import id.thesis.shumishumi.dalgen.model.request.UserDAORequest;
import id.thesis.shumishumi.dalgen.model.result.UserDO;
import id.thesis.shumishumi.dalgen.service.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: UserDAOImpl.java, v 0.1 2022‐12‐26 7:30 AM Aldih Suhandi Exp $$
 */
@Service
public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(UserDAORequest daoRequest) throws ShumishumiException {
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER, DatabaseConst.STATEMENT_INSERT)
                .addValueStatement(DatabaseConst.USER_ID)
                .addValueStatement(DatabaseConst.EMAIL)
                .addValueStatement(DatabaseConst.PHONE_NUMBER)
                .addValueStatement(DatabaseConst.USERNAME)
                .addValueStatement(DatabaseConst.ROLE_ID)
                .addValueStatement(DatabaseConst.PASSWORD)
                .buildStatement();

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
    public void update(UserDAORequest daoRequest) throws ShumishumiException {
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.EMAIL)
                .addSetStatement(DatabaseConst.USERNAME)
                .addSetStatement(DatabaseConst.PHONE_NUMBER)
                .addSetStatement(DatabaseConst.PROFILE_PICTURE)
                .addSetStatement(DatabaseConst.PASSWORD)
                .addSetStatement(DatabaseConst.GMT_MODIFIED)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.USER_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, daoRequest.getEmail());
                ps.setString(2, daoRequest.getUsername());
                ps.setString(3, daoRequest.getPhoneNumber());
                ps.setBlob(4, daoRequest.getProfilePicture());
                ps.setString(5, daoRequest.getPassword());
                ps.setTimestamp(6, new Timestamp(daoRequest.getGmtModified().getTime()));
                ps.setString(7, daoRequest.getUsername());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void updateProfilePicture(UserDAORequest daoRequest) throws ShumishumiException {
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.PROFILE_PICTURE)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.USER_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

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
    public UserDO queryById(UserDAORequest daoRequest) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.USER_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<UserDO> userDOS = jdbcTemplate.query(statement, ps ->
                ps.setString(1, daoRequest.getUserId()), new UserDOMapper());

        if (userDOS.isEmpty()) {
            return null;
        }

        return userDOS.get(0);
    }

    @Override
    public UserDO queryByEmail(UserDAORequest daoRequest) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.EMAIL, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<UserDO> userDOS = jdbcTemplate.query(statement, ps ->
                ps.setString(1, daoRequest.getEmail()), new UserDOMapper());

        if (userDOS.isEmpty()) {
            return null;
        }

        return userDOS.get(0);
    }

    @Override
    public UserDO queryByPhoneNumber(UserDAORequest daoRequest) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_USER, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.PHONE_NUMBER, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<UserDO> userDOS = jdbcTemplate.query(statement, ps ->
                ps.setString(1, daoRequest.getPhoneNumber()), new UserDOMapper());

        if (userDOS.isEmpty()) {
            return null;
        }

        return userDOS.get(0);
    }
}
