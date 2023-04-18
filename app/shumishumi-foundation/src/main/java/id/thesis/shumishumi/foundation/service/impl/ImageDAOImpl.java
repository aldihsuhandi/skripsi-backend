package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.common.util.database.StatementBuilder;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.mapper.ImageDOMapper;
import id.thesis.shumishumi.foundation.model.request.ImageDAORequest;
import id.thesis.shumishumi.foundation.model.result.ImageDO;
import id.thesis.shumishumi.foundation.service.ImageDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageDAOImpl implements ImageDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    private static final Logger DAO_LOGGER = LoggerFactory.
            getLogger(LogConstant.DAO_LOGGER);

    @Override
    public void insert(ImageDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("imageDAO#insert[request=%s]", request));
        String statement = new StatementBuilder(DatabaseConst.TABLE_IMAGE, DatabaseConst.STATEMENT_INSERT)
                .addValueStatement(DatabaseConst.IMAGE_ID)
                .addValueStatement(DatabaseConst.IMAGE_NAME)
                .addValueStatement(DatabaseConst.IMAGE_EXT)
                .addValueStatement(DatabaseConst.IMAGE)
                .buildStatement();
        LogUtil.info(DAO_LOGGER, "statement: " + statement);

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, request.getImageId());
                ps.setString(2, request.getImageName());
                ps.setString(3, request.getImageExt());
                ps.setBlob(4, request.getImage());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public ImageDO query(ImageDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("imageDAO#query[request=%s]", request));
        String statement = new StatementBuilder(DatabaseConst.TABLE_IMAGE, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.IMAGE_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();
        LogUtil.info(DAO_LOGGER, "statement: " + statement);

        List<ImageDO> images = jdbcTemplate.query(statement, ps ->
                ps.setString(1, request.getImageId()), new ImageDOMapper());
        if (images.isEmpty()) {
            LogUtil.info(DALGEN_LOGGER, "imageDAO#query[result=null]");
            return null;
        }

        LogUtil.info(DALGEN_LOGGER, String.format("imageDAO#query[result=%s]", images.get(0)));
        return images.get(0);
    }
}
