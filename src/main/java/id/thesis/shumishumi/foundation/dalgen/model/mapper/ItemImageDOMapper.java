package id.thesis.shumishumi.foundation.dalgen.model.mapper;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.foundation.dalgen.model.result.ItemImageDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemImageDOMapper implements RowMapper<ItemImageDO> {
    @Override
    public ItemImageDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemImageDO itemImageDO = new ItemImageDO();
        itemImageDO.setItemImageId(rs.getString(DatabaseConst.ITEM_IMAGE_ID));
        itemImageDO.setItemImage(rs.getBlob(DatabaseConst.ITEM_IMAGE));
        itemImageDO.setItemId(rs.getString(DatabaseConst.ITEM_ID));
        itemImageDO.setDeleted(rs.getBoolean(DatabaseConst.IS_DELETED));
        itemImageDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        itemImageDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return itemImageDO;
    }
}
