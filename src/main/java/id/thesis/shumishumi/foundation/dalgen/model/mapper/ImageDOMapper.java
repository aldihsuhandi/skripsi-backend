package id.thesis.shumishumi.foundation.dalgen.model.mapper;

import id.thesis.shumishumi.common.util.constant.DatabaseConst;
import id.thesis.shumishumi.foundation.dalgen.model.result.ImageDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageDOMapper implements RowMapper<ImageDO> {
    @Override
    public ImageDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ImageDO imageDO = new ImageDO();
        imageDO.setImageId(rs.getString(DatabaseConst.IMAGE_ID));
        imageDO.setImageName(rs.getString(DatabaseConst.IMAGE_NAME));
        imageDO.setImageExt(rs.getString(DatabaseConst.IMAGE_EXT));
        imageDO.setImage(rs.getBlob(DatabaseConst.IMAGE));
        imageDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
        imageDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

        return imageDO;
    }
}
