/**
 * 
 *
 */
package id.thesis.shumishumi.foundation.dalgen.model.mapper;


import id.thesis.shumishumi.common.constant.DatabaseConst;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: CountMapper.java, v 0.1 2023‐01‐17 10:06 Aldih Suhandi Exp $$
 */
public class CountMapper implements RowMapper<Integer> {
    @Override
    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt(DatabaseConst.COUNT);
    }
}
