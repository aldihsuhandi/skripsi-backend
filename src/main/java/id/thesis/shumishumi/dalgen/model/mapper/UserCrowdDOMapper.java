package id.thesis.shumishumi.dalgen.model.mapper;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.dalgen.model.result.UserCrowdDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCrowdDOMapper implements RowMapper<UserCrowdDO> {
    @Override
    public UserCrowdDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserCrowdDO userCrowdDO = new UserCrowdDO();
        userCrowdDO.setUserId(rs.getString(DatabaseConst.USER_ID));
        userCrowdDO.setCrowdId(rs.getString(DatabaseConst.CROWD_ID));
        userCrowdDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        userCrowdDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return userCrowdDO;
    }
}