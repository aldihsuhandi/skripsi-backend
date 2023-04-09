package id.thesis.shumishumi.foundation.dalgen.service.impl;

import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.common.util.constant.DatabaseConst;
import id.thesis.shumishumi.common.util.constant.LogConstant;
import id.thesis.shumishumi.foundation.dalgen.model.request.PostDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.result.PostDO;
import id.thesis.shumishumi.foundation.dalgen.service.PostDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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
    }

    @Override
    public void delete(String postId) {
        LogUtil.info(DALGEN, String.format("postDAO#delete[postId=%s]", postId));
    }

    @Override
    public PostDO queryById(String postId) {
        LogUtil.info(DALGEN, String.format("postDAO#queryById[postId=%s]", postId));
        return null;
    }

    @Override
    public List<PostDO> query(PostDAORequest request) {
        LogUtil.info(DALGEN, String.format("postDAO#query[request=%s]", request));
        return null;
    }
}
