/**
 * 
 *
 */
package id.thesis.shumishumi.foundation.dalgen.model.mapper;


import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.foundation.dalgen.model.result.ActivityDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ActivityDOMapper.java, v 0.1 2023‐01‐19 9:38 AM Aldih Suhandi Exp $$
 */
public class ActivityDOMapper implements RowMapper<ActivityDO> {
    @Override
    public ActivityDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ActivityDO activityDO = new ActivityDO();
        activityDO.setActivityId(rs.getString(DatabaseConst.ACTIVITY_ID));
        activityDO.setActivityName(rs.getString(DatabaseConst.ACTIVITY_NAME));
        activityDO.setPoint(rs.getInt(DatabaseConst.ACTIVITY_POINT));
        activityDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        activityDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return activityDO;
    }
}
