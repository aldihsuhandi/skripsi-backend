package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.service.CommentService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.converter.ViewObjectConverter;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.viewobject.CommentVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.foundation.model.result.CommentDO;
import id.thesis.shumishumi.foundation.service.CommentDAO;
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
    public List<CommentVO> query(String replyId, String replyType) {
        if (StringUtils.equals(replyType, CommonConst.COMMENT_REPLY_COMMENT)) {
            return commentDAO.queryCommentComment(replyId).stream().map(comment -> {
                UserVO userVO = userService.queryById(comment.getUserId(), true);
                return ViewObjectConverter.toViewObject(comment, userVO);
            }).collect(Collectors.toList());
        }

        if (StringUtils.equals(replyType, CommonConst.COMMENT_REPLY_POST)) {
            return commentDAO.queryPostComment(replyId).stream().map(comment -> {
                UserVO userVO = userService.queryById(comment.getUserId(), true);
                return ViewObjectConverter.toViewObject(comment, userVO);
            }).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public CommentVO queryById(String commentId) {
        return ViewObjectConverter.toViewObject(commentDAO.queryById(commentId), null);
    }
}
