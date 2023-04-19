package id.thesis.shumishumi.foundation.model.mapper;

import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.foundation.model.result.ItemDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDOMapper implements RowMapper<ItemDO> {
    @Override
    public ItemDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemDO itemDO = new ItemDO();
        itemDO.setItemId(rs.getString(DatabaseConst.ITEM_ID));
        itemDO.setItemName(rs.getString(DatabaseConst.ITEM_NAME));
        itemDO.setItemDescription(rs.getString(DatabaseConst.ITEM_DESCRIPTION));
        itemDO.setItemPrice(rs.getLong(DatabaseConst.ITEM_PRICE));
        itemDO.setItemQuantity(rs.getInt(DatabaseConst.ITEM_QUANTITY));
        itemDO.setCategoryId(rs.getString(DatabaseConst.CATEGORY_ID));
        itemDO.setMerchantId(rs.getString(DatabaseConst.MERCHANT_ID));
        itemDO.setMerchantLevelId(rs.getString(DatabaseConst.MERCHANT_LEVEL_ID));
        itemDO.setHobbyId(rs.getString(DatabaseConst.HOBBY_ID));
        itemDO.setApproved(rs.getBoolean(DatabaseConst.IS_APPROVED));
        itemDO.setDeleted(rs.getBoolean(DatabaseConst.IS_DELETED));
        itemDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        itemDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return itemDO;
    }
}
