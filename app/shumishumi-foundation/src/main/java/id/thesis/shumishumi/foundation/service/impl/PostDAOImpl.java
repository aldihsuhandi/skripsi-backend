package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.common.util.database.StatementBuilder;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.mapper.CountMapper;
import id.thesis.shumishumi.foundation.model.mapper.PostDOMapper;
import id.thesis.shumishumi.foundation.model.request.PostDAORequest;
import id.thesis.shumishumi.foundation.model.result.PostDO;
import id.thesis.shumishumi.foundation.service.PostDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class PostDAOImpl implements PostDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger DALGEN = LoggerFactory.getLogger(LogConstant.DALGEN_LOGGER);
    private static final Logger DAO = LoggerFactory.getLogger(LogConstant.DAO_LOGGER);

    @Override
    public void insert(PostDAORequest request) {
        LogUtil.info(DALGEN, String.format("postDAO#insert[request=%s]", request));
        String statement = new StatementBuilder(DatabaseConst.TABLE_POST, DatabaseConst.STATEMENT_INSERT)
                .addValueStatement(DatabaseConst.POST_ID)
                .addValueStatement(DatabaseConst.USER_ID)
                .addValueStatement(DatabaseConst.POST_TITLE)
                .addValueStatement(DatabaseConst.POST_CONTENT)
                .addValueStatement(DatabaseConst.POST_TAGS)
                .addValueStatement(DatabaseConst.POST_IMAGES)
                .buildStatement();
        LogUtil.info(DAO, "statement", statement);

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, request.getPostId());
                ps.setString(2, request.getUserId());
                ps.setString(3, request.getTitle());
                ps.setString(4, request.getContent());
                ps.setString(5, request.getTags());
                ps.setString(6, request.getImages());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void update(PostDAORequest request) {
        LogUtil.info(DALGEN, String.format("postDAO#update[request=%s]", request));
        String statement = new StatementBuilder(DatabaseConst.TABLE_POST, DatabaseConst.STATEMENT_UPDATE)
                .addValueStatement(DatabaseConst.POST_TITLE)
                .addValueStatement(DatabaseConst.POST_CONTENT)
                .addValueStatement(DatabaseConst.POST_TAGS)
                .addValueStatement(DatabaseConst.POST_IMAGES)
                .addValueStatement(DatabaseConst.GMT_MODIFIED)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.POST_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();
        LogUtil.info(DAO, "statement", statement);

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, request.getTitle());
                ps.setString(2, request.getContent());
                ps.setString(3, request.getTags());
                ps.setString(4, request.getImages());
                ps.setTimestamp(5, new Timestamp(new Date().getTime()));
                ps.setString(6, request.getPostId());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void delete(String postId) {
        LogUtil.info(DALGEN, String.format("postDAO#delete[postId=%s]", postId));
        String statement = new StatementBuilder(DatabaseConst.TABLE_POST, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.IS_DELETED)
                .addValueStatement(DatabaseConst.GMT_MODIFIED)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.POST_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();
        LogUtil.info(DAO, "statement", statement);

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setBoolean(1, true);
                ps.setTimestamp(2, new Timestamp(new Date().getTime()));
                ps.setString(3, postId);
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public PostDO queryById(String postId) {
        LogUtil.info(DALGEN, String.format("postDAO#queryById[postId=%s]", postId));
        String statement = new StatementBuilder(DatabaseConst.TABLE_POST, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.POST_ID, DatabaseConst.COMPARATOR_EQUAL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.IS_DELETED, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();
        LogUtil.info(DAO, "statement", statement);

        List<PostDO> posts = jdbcTemplate.query(statement, ps -> {
            ps.setString(1, postId);
            ps.setBoolean(2, false);
        }, new PostDOMapper());

        if (posts.isEmpty()) {
            LogUtil.info(DALGEN, "postDAO#queryById[result=null]");
            return null;
        }

        LogUtil.info(DALGEN, String.format("postDAO#queryById[result=%s]", posts.get(0)));

        return posts.get(0);
    }

    @Override
    public List<PostDO> query(PostDAORequest request) {
        LogUtil.info(DALGEN, String.format("postDAO#query[request=%s]", request));
        StatementBuilder sb = new StatementBuilder(DatabaseConst.TABLE_POST, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addLimitStatement(request.getPagingContext())
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.IS_DELETED, DatabaseConst.COMPARATOR_EQUAL)
                .addWhereCaseInsensitiveStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.POST_TITLE, DatabaseConst.COMPARATOR_LIKE);


        List<String> tags = List.of(request.getTags().split(CommonConst.SEPARATOR_SPLIT));
        for (int i = 0; i < tags.size(); ++i) {
            sb.addWhereCaseInsensitiveStatement(DatabaseConst.APPEND_OPERATOR_AND,
                    DatabaseConst.POST_TAGS, DatabaseConst.COMPARATOR_LIKE);
        }

        String statement = sb.buildStatement();
        LogUtil.info(DAO, "statement", statement);


        List<PostDO> posts = jdbcTemplate.query(statement, ps -> {
            ps.setBoolean(1, false);
            ps.setString(2, request.getTitle());
            int cnt = 3;
            for (String tag : tags) {
                ps.setString(cnt, tag);
                ++cnt;
            }
        }, new PostDOMapper());

        LogUtil.info(DALGEN, String.format("postDAO#query[result=%s]", posts));
        return posts;
    }

    @Override
    public int countList(PostDAORequest request) {
        LogUtil.info(DALGEN, String.format("postDAO#countList[request=%s]", request));
        StatementBuilder sb = new StatementBuilder(DatabaseConst.TABLE_POST, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_COUNT)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.IS_DELETED, DatabaseConst.COMPARATOR_EQUAL)
                .addWhereCaseInsensitiveStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.POST_TITLE, DatabaseConst.COMPARATOR_LIKE);

        List<String> tags = List.of(request.getTags().split(CommonConst.SEPARATOR_SPLIT));
        for (int i = 0; i < tags.size(); ++i) {
            sb.addWhereCaseInsensitiveStatement(DatabaseConst.APPEND_OPERATOR_AND,
                    DatabaseConst.POST_TAGS, DatabaseConst.COMPARATOR_LIKE);
        }

        String statement = sb.buildStatement();
        LogUtil.info(DAO, "statement", statement);

        int count = jdbcTemplate.query(statement, ps -> {
            ps.setBoolean(1, false);
            ps.setString(2, request.getTitle());
            int cnt = 3;
            for (String tag : tags) {
                ps.setString(cnt, tag);
                ++cnt;
            }
        }, new CountMapper()).get(0);
        LogUtil.info(DALGEN, String.format("postDAO#countList[result=%s]", count));
        return count;
    }
}
