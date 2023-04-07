/**
 * 
 *
 */
package id.thesis.shumishumi.foundation.dalgen.model.mapper;

import id.thesis.shumishumi.common.util.constant.DatabaseConst;
import id.thesis.shumishumi.foundation.dalgen.model.result.ItemCategoryDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ItemCategoryMapper.java, v 0.1 2023‐01‐17 13:21 Aldih Suhandi Exp $$
 */
public class ItemCategoryDOMapper implements RowMapper<ItemCategoryDO> {
    @Override
    public ItemCategoryDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemCategoryDO categoryDO = new ItemCategoryDO();
        categoryDO.setCategoryId(rs.getString(DatabaseConst.CATEGORY_ID));
        categoryDO.setCategoryName(rs.getString(DatabaseConst.CATEGORY_NAME));
        categoryDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        categoryDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return categoryDO;
    }
}
