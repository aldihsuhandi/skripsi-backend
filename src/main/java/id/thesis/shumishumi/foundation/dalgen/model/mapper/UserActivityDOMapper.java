/**
 * 
 *
 */
package id.thesis.shumishumi.foundation.dalgen.model.mapper;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.foundation.dalgen.model.result.UserActivityDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserActivityDOMapper.java, v 0.1 2023‐01‐19 9:33 AM Aldih Suhandi Exp $$
 */
public class UserActivityDOMapper implements RowMapper<UserActivityDO> {
    @Override
    public UserActivityDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserActivityDO userActivityDO = new UserActivityDO();
        userActivityDO.setUserActivityId(rs.getString(DatabaseConst.USER_ACTIVITY_ID));
        userActivityDO.setActivityId(rs.getString(DatabaseConst.ACTIVITY_ID));
        userActivityDO.setUserId(rs.getString(DatabaseConst.USER_ID));
        userActivityDO.setItemId(rs.getString(DatabaseConst.ITEM_ID));
        userActivityDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        userActivityDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return userActivityDO;
    }
}
