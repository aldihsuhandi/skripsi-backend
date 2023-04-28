package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.service.CommentService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.converter.ViewObjectConverter;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.viewobject.CommentVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.foundation.model.result.CommentDO;
import id.thesis.shumishumi.foundation.model.result.CommentVoteDO;
import id.thesis.shumishumi.foundation.service.CommentDAO;
import id.thesis.shumishumi.foundation.service.CommentVoteDAO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private CommentVoteDAO commentVoteDAO;

    @Autowired
    private UserService userService;

    @Override
    public String create(String userId, String content, String replyId, String replyType) {
        CommentDO commentDO = new CommentDO();
        commentDO.setCommentId(FunctionUtil.generateUUID());
        commentDO.setContent(content);
        commentDO.setUserId(userId);
        if (StringUtils.equals(replyType, CommonConst.COMMENT_REPLY_COMMENT)) {
            commentDO.setReplyCommentId(replyId);
        } else if (StringUtils.equals(replyType, CommonConst.COMMENT_REPLY_POST)) {
            commentDO.setReplyPostId(replyId);
        }

        commentDAO.create(commentDO);

        return commentDO.getCommentId();
    }

    @Override
    public void update(String commentId, String content) {
        commentDAO.update(commentId, content);
    }

    @Override
    public void delete(String commentId) {
        commentDAO.delete(commentId);
    }

    @Override
    public List<CommentVO> query(String replyId, String replyType, PagingContext pagingContext) {
        if (StringUtils.equals(replyType, CommonConst.COMMENT_REPLY_COMMENT)) {
            return commentDAO.queryCommentComment(replyId, pagingContext).stream().map(comment -> {
                UserVO userVO = userService.queryById(comment.getUserId(), true);
                CommentVO commentVO = ViewObjectConverter.toViewObject(comment, userVO);
                composeCommentVote(commentVO);

                return commentVO;
            }).collect(Collectors.toList());
        }

        if (StringUtils.equals(replyType, CommonConst.COMMENT_REPLY_POST)) {
            return commentDAO.queryPostComment(replyId, pagingContext).stream().map(comment -> {
                UserVO userVO = userService.queryById(comment.getUserId(), true);
                CommentVO commentVO = ViewObjectConverter.toViewObject(comment, userVO);
                composeCommentVote(commentVO);

                return commentVO;
            }).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public CommentVO queryById(String commentId) {
        CommentVO commentVO = ViewObjectConverter.toViewObject(commentDAO.queryById(commentId), null);
        composeCommentVote(commentVO);

        return commentVO;
    }

    @Override
    public int queryVote(String userId, String commentId) {
        CommentVoteDO voteDO = commentVoteDAO.queryUserVote(commentId, userId);
        if (voteDO == null) {
            return CommonConst.INT_NOT_FOUND;
        }
        return voteDO.getValue();
    }

    @Override
    public void insertVote(String userId, String commentId, int value) {
        commentVoteDAO.insert(userId, commentId, value);
    }

    @Override
    public void updateVote(String userId, String commentId, int value) {
        commentVoteDAO.update(userId, commentId, value);
    }

    private void composeCommentVote(CommentVO comment) {
        if (comment == null) {
            return;
        }

        int upvote = commentVoteDAO.queryVote(comment.getCommentId(), 1);
        int downvote = commentVoteDAO.queryVote(comment.getCommentId(), -1);

        comment.setUpvote(upvote);
        comment.setDownvote(downvote);
    }
}
