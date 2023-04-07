package id.thesis.shumishumi.foundation.dalgen.model.mapper;

import id.thesis.shumishumi.common.util.constant.DatabaseConst;
import id.thesis.shumishumi.foundation.dalgen.model.result.SessionDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionDOMapper implements RowMapper<SessionDO> {
    @Override
    public SessionDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        SessionDO sessionDO = new SessionDO();
        sessionDO.setSessionId(rs.getString(DatabaseConst.SESSION_ID));
        sessionDO.setUserId(rs.getString(DatabaseConst.USER_ID));
        sessionDO.setActive(rs.getBoolean(DatabaseConst.IS_ACTIVE));
        sessionDO.setRemembered(rs.getBoolean(DatabaseConst.IS_REMEMBERED));
        sessionDO.setSessionDt(rs.getTimestamp(DatabaseConst.SESSION_DT));
        sessionDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        sessionDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return sessionDO;
    }
}
