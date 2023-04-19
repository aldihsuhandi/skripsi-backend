package id.thesis.shumishumi.foundation.model.mapper;


import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.foundation.model.result.InterestLevelDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InterestLevelDOMapper implements RowMapper<InterestLevelDO> {
    @Override
    public InterestLevelDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        InterestLevelDO interestLevelDO = new InterestLevelDO();
        interestLevelDO.setInterestLevelId(rs.getString(DatabaseConst.INTEREST_LEVEL_ID));
        interestLevelDO.setInterestLevelName(rs.getString(DatabaseConst.INTEREST_LEVEL_NAME));
        interestLevelDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        interestLevelDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return interestLevelDO;
    }
}
