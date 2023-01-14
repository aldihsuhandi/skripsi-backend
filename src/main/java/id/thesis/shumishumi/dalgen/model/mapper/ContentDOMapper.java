package id.thesis.shumishumi.dalgen.model.mapper;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.dalgen.model.result.ContentDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContentDOMapper implements RowMapper<ContentDO> {
    @Override
    public ContentDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ContentDO contentDO = new ContentDO();
        contentDO.setContentName(rs.getString(DatabaseConst.CONTENT_NAME));
        contentDO.setContent(rs.getString(DatabaseConst.CONTENT));
        contentDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        contentDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return contentDO;
    }
}