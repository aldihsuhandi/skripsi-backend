package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.result.CommentDO;

import java.util.List;

public interface CommentDAO {
    void create(CommentDO commentDO);

    void update(String commentId, String content);

    void delete(String commentId);

    List<CommentDO> queryPostComment(String postId);

    List<CommentDO> queryCommentComment(String commentId);

    CommentDO queryById(String commentId);
}
