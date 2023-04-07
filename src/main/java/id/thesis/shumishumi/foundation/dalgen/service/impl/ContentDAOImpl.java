package id.thesis.shumishumi.foundation.dalgen.service.impl;

import id.thesis.shumishumi.common.util.constant.DatabaseConst;
import id.thesis.shumishumi.common.util.constant.LogConstant;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.foundation.dalgen.model.mapper.ContentDOMapper;
import id.thesis.shumishumi.foundation.dalgen.model.result.ContentDO;
import id.thesis.shumishumi.foundation.dalgen.service.ContentDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentDAOImpl implements ContentDAO {
    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    private static final Logger DAO_LOGGER = LoggerFactory.
            getLogger(LogConstant.DAO_LOGGER);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ContentDO queryContent(String contentName) {
        LogUtil.info(DALGEN_LOGGER, contentName);

        String statement = new StatementBuilder(DatabaseConst.TABLE_CONTENT, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.CONTENT_NAME, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        LogUtil.info(DAO_LOGGER, "statement", statement);

        List<ContentDO> contentDOs = jdbcTemplate.query(statement, ps ->
                ps.setString(1, contentName), new ContentDOMapper());

        if (contentDOs.isEmpty()) {
            return null;
        }

        LogUtil.info(DALGEN_LOGGER, contentDOs);

        return contentDOs.get(0);
    }
}
