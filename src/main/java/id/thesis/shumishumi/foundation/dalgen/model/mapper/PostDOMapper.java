package id.thesis.shumishumi.foundation.dalgen.model.mapper;

import id.thesis.shumishumi.common.util.constant.DatabaseConst;
import id.thesis.shumishumi.foundation.dalgen.model.result.PostDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class PostDOMapper implements RowMapper<PostDO> {
    @Override
    public PostDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        PostDO postDO = new PostDO();
        postDO.setPostId(rs.getString(DatabaseConst.POST_ID));
        postDO.setTitle(rs.getString(DatabaseConst.POST_TITLE));
        postDO.setContent(rs.getString(DatabaseConst.POST_CONTENT));
        postDO.setTags(rs.getString(DatabaseConst.POST_TAGS));
        postDO.setImages(rs.getString(DatabaseConst.POST_IMAGES));
        postDO.setDeleted(rs.getBoolean(DatabaseConst.IS_DELETED));
        postDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        postDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return null;
    }
}
