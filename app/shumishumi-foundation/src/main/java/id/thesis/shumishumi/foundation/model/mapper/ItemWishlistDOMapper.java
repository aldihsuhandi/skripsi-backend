package id.thesis.shumishumi.foundation.model.mapper;

import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.foundation.model.result.ItemWishlistDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemWishlistDOMapper implements RowMapper<ItemWishlistDO> {
    @Override
    public ItemWishlistDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemWishlistDO wishlistDO = new ItemWishlistDO();
        wishlistDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        wishlistDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return wishlistDO;
    }
}
