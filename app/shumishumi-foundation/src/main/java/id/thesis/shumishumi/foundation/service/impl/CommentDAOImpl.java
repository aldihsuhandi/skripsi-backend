package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.CommentDO;
import id.thesis.shumishumi.foundation.repository.CommentRepository;
import id.thesis.shumishumi.foundation.service.CommentDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentDAOImpl implements CommentDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void create(CommentDO commentDO) {
        LogUtil.info(LOGGER, String.format("commentDAO#create[request=%s]", commentDO));
        try {
            commentRepository.save(commentDO);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void update(String commentId, String content) {
        LogUtil.info(LOGGER, String.format("commentDAO#update[commentId=%s,content=%s]", commentId, content));
        try {
            commentRepository.updateComment(commentId, content);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void delete(String commentId) {
        LogUtil.info(LOGGER, String.format("commentDAO#delete[commentId=%s]", commentId));
        try {
            commentRepository.softDelete(commentId);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public List<CommentDO> queryPostComment(String postId) {
        LogUtil.info(LOGGER, String.format("commentDAO#queryPostComment[postId=%s]", postId));

        List<CommentDO> result;
        try {
            result = commentRepository.findByReplyPostId(postId);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
        LogUtil.info(LOGGER, String.format("commentDAO#queryPostComment[result=%s]", result));
        return result;
    }

    @Override
    public List<CommentDO> queryCommentComment(String commentId) {
        LogUtil.info(LOGGER, String.format("commentDAO#queryCommentComment[commentId=%s]", commentId));

        List<CommentDO> result;
        try {
            result = commentRepository.findByReplyCommentId(commentId);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
        LogUtil.info(LOGGER, String.format("commentDAO#queryCommentComment[result=%s]", result));
        return result;
    }

    @Override
    public CommentDO queryById(String commentId) {
        LogUtil.info(LOGGER, String.format("commentDAO#queryById[commentId=%s]", commentId));

        CommentDO commentDO;
        try {
            commentDO = commentRepository.findById(commentId).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
        LogUtil.info(LOGGER, String.format("commentDAO#queryById[result=%s]", commentDO));
        return commentDO;
    }
}
