package id.thesis.shumishumi.foundation.dalgen.model.mapper;

import id.thesis.shumishumi.common.util.constant.DatabaseConst;
import id.thesis.shumishumi.foundation.dalgen.model.result.ItemWishlistDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemWishlistDOMapper implements RowMapper<ItemWishlistDO> {
    @Override
    public ItemWishlistDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemWishlistDO wishlistDO = new ItemWishlistDO();
        wishlistDO.setItemId(rs.getString(DatabaseConst.ITEM_ID));
        wishlistDO.setUserId(rs.getString(DatabaseConst.USER_ID));
        wishlistDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        wishlistDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return wishlistDO;
    }
}
