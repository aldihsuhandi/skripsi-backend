package id.thesis.shumishumi.foundation.dalgen.service.impl;

import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.common.util.constant.DatabaseConst;
import id.thesis.shumishumi.common.util.constant.LogConstant;
import id.thesis.shumishumi.foundation.dalgen.model.result.PostVoteDO;
import id.thesis.shumishumi.foundation.dalgen.service.PostVoteDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class PostVoteDAOImpl implements PostVoteDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger DAO = LoggerFactory.getLogger(LogConstant.DAO_LOGGER);
    private static final Logger DALGEN = LoggerFactory.getLogger(LogConstant.DALGEN_LOGGER);

    @Override
    public int queryVote(String postId, int value) {
        LogUtil.info(DALGEN, String.format("postVoteDAO#queryVote[postId=%s,value=%d]", postId, value));
        return 0;
    }

    @Override
    public PostVoteDO queryUserVote(String postId, String userId) {
        LogUtil.info(DALGEN, String.format("postVoteDAO#queryUserVote[postId=%s,userId=%s]",
                postId, userId));
        String statement = new StatementBuilder(DatabaseConst.TABLE_POST_UPVOTE, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.POST_ID, DatabaseConst.COMPARATOR_EQUAL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.USER_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();
        LogUtil.info(DAO, "statement", statement);

        List<PostVoteDO> result = jdbcTemplate.query(statement, ps -> {
            ps.setString(1, postId);
            ps.setString(2, userId);
        }, (rs, rowNum) -> {
            PostVoteDO postVoteDO = new PostVoteDO();
            postVoteDO.setPostId(rs.getString(DatabaseConst.POST_ID));
            postVoteDO.setUserId(rs.getString(DatabaseConst.USER_ID));
            postVoteDO.setValue(rs.getInt(DatabaseConst.UPVOTES_VALUE));
            postVoteDO.setGmtCreate(rs.getTimestamp(DatabaseConst.GMT_CREATE));
            postVoteDO.setGmtModified(rs.getTimestamp(DatabaseConst.GMT_MODIFIED));

            return postVoteDO;
        });

        if(result.isEmpty()) {
            LogUtil.info(DALGEN, "postVoteDAO#queryUserVote[result=null]");
            return null;
        }

        LogUtil.info(DALGEN, String.format("postVoteDAO#queryUserVote[result=%s]", result.get(0)));

        return result.get(0);
    }

    @Override
    public void insert(String userId, String postId, int value) {
        LogUtil.info(DALGEN, String.format("postVoteDAO#insert[userId=%s,postId=%s,value=%s]"
                , userId, postId, value));
        String statement = new StatementBuilder(DatabaseConst.TABLE_POST_UPVOTE, DatabaseConst.STATEMENT_INSERT)
                .addValueStatement(DatabaseConst.POST_ID)
                .addValueStatement(DatabaseConst.USER_ID)
                .addValueStatement(DatabaseConst.UPVOTES_VALUE)
                .buildStatement();
        LogUtil.info(DAO, "statement", statement);

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, postId);
                ps.setString(2, userId);
                ps.setInt(3, value);
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void update(String userId, String postId, int value) {

    }
}
