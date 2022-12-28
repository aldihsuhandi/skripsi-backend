package id.thesis.shumishumi.dalgen.model.mapper;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.dalgen.model.result.RoleDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDOMapper implements RowMapper<RoleDO> {
    @Override
    public RoleDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        RoleDO roleDO = new RoleDO();
        roleDO.setRoleId(rs.getString(DatabaseConst.ROLE_ID));
        roleDO.setRoleName(rs.getString(DatabaseConst.ROLE_NAME));
        roleDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        roleDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return roleDO;
    }
}
