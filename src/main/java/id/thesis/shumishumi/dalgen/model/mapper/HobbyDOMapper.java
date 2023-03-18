/**
 * 
 *
 */
package id.thesis.shumishumi.dalgen.model.mapper;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.dalgen.model.result.HobbyDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: HobbyDoMapper.java, v 0.1 2023‐01‐17 13:27 Aldih Suhandi Exp $$
 */
public class HobbyDOMapper implements RowMapper<HobbyDO> {
    @Override
    public HobbyDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        HobbyDO hobbyDO = new HobbyDO();
        hobbyDO.setHobbyId(rs.getString(DatabaseConst.CATEGORY_ID));
        hobbyDO.setHobbyName(rs.getString(DatabaseConst.CATEGORY_NAME));
        hobbyDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        hobbyDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return hobbyDO;
    }
}
