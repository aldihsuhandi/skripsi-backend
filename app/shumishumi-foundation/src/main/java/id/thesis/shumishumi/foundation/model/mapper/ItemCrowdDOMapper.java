package id.thesis.shumishumi.foundation.model.mapper;

import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.foundation.model.result.ItemCrowdDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemCrowdDOMapper implements RowMapper<ItemCrowdDO> {
    @Override
    public ItemCrowdDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemCrowdDO itemCrowdDO = new ItemCrowdDO();
        itemCrowdDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        itemCrowdDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return itemCrowdDO;
    }
}
