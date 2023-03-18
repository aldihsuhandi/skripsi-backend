/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.dalgen.model.mapper;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.dalgen.model.result.UserDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserDOMapper.java, v 0.1 2022‐12‐26 2:24 PM Aldih Suhandi Exp $$
 */
public class UserDOMapper implements RowMapper<UserDO> {
    @Override
    public UserDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserDO userDO = new UserDO();
        userDO.setUserId(rs.getString(DatabaseConst.USER_ID));
        userDO.setUsername(rs.getString(DatabaseConst.USERNAME));
        userDO.setPhoneNumber(rs.getString(DatabaseConst.PHONE_NUMBER));
        userDO.setEmail(rs.getString(DatabaseConst.EMAIL));
        userDO.setProfilePicture(rs.getBlob(DatabaseConst.PROFILE_PICTURE));
        userDO.setRoleId(rs.getString(DatabaseConst.ROLE_ID));
        userDO.setPassword(rs.getString(DatabaseConst.PASSWORD));
        userDO.setDeleted(rs.getBoolean(DatabaseConst.IS_DELETED));
        userDO.setActive(rs.getBoolean(DatabaseConst.IS_ACTIVE));
        userDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        userDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return userDO;
    }
}
