package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.CommentVoteDO;
import id.thesis.shumishumi.foundation.model.result.primarykey.CommentVoteDOPK;
import id.thesis.shumishumi.foundation.repository.CommentVoteRepository;
import id.thesis.shumishumi.foundation.service.CommentVoteDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentVoteDAOImpl implements CommentVoteDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private CommentVoteRepository commentVoteRepository;

    @Override
    public int queryVote(String commentId, int value) {
        LogUtil.info(LOGGER, String.format("commentVoteDAO#queryVote[commentId=%s,value=%d]", commentId, value));
        int count = 0;
        try {
            count = commentVoteRepository.countCommentVote(commentId, value);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("commentVoteDAO#queryVote[result=%d]", count));
        return count;
    }

    @Override
    public CommentVoteDO queryUserVote(String commentId, String userId) {
        LogUtil.info(LOGGER, String.format("commentVoteDAO#queryUserVote[commentId=%s,userId=%s]", commentId, userId));
        CommentVoteDO result;
        try {
            result = commentVoteRepository.findById(new CommentVoteDOPK(userId, commentId)).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("commentVoteDAO#queryUserVote[result=%s]", result));
        return result;
    }

    @Override
    public void insert(String userId, String commentId, int value) {
        LogUtil.info(LOGGER, String.format("commentVoteDAO#insert[userId=%s,commentId=%s,value=%d]"
                , userId, commentId, value));
        CommentVoteDO comment = new CommentVoteDO();
        comment.setPk(new CommentVoteDOPK(userId, commentId));
        comment.setValue(value);
        try {
            commentVoteRepository.save(comment);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void update(String userId, String commentId, int value) {
        LogUtil.info(LOGGER, String.format("commentVoteDAO#update[userId=%s,commentId=%s,value=%d]"
                , userId, commentId, value));
        CommentVoteDO comment = new CommentVoteDO();
        comment.setPk(new CommentVoteDOPK(userId, commentId));
        comment.setValue(value);
        try {
            commentVoteRepository.save(comment);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }
}
