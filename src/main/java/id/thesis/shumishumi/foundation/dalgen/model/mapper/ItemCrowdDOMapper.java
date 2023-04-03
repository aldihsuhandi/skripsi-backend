package id.thesis.shumishumi.foundation.dalgen.model.mapper;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.foundation.dalgen.model.result.ItemCrowdDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemCrowdDOMapper implements RowMapper<ItemCrowdDO> {
    @Override
    public ItemCrowdDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemCrowdDO itemCrowdDO = new ItemCrowdDO();
        itemCrowdDO.setItemId(rs.getString(DatabaseConst.ITEM_ID));
        itemCrowdDO.setCrowdId(rs.getString(DatabaseConst.CROWD_ID));
        itemCrowdDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        itemCrowdDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return itemCrowdDO;
    }
}
