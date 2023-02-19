package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.foundation.model.mapper.ContentDOMapper;
import id.thesis.shumishumi.foundation.model.result.ContentDO;
import id.thesis.shumishumi.foundation.service.ContentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentDAOImpl implements ContentDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ContentDO queryContent(String contentName) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_CONTENT, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.CONTENT_NAME, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<ContentDO> contentDOs = jdbcTemplate.query(statement, ps ->
                ps.setString(1, contentName), new ContentDOMapper());

        if (contentDOs.isEmpty()) {
            return null;
        }

        return contentDOs.get(0);
    }
}
