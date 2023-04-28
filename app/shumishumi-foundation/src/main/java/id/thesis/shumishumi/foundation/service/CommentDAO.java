package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.foundation.model.result.CommentDO;

import java.util.List;

public interface CommentDAO {
    void create(CommentDO commentDO);

    void update(String commentId, String content);

    void delete(String commentId);

    List<CommentDO> queryPostComment(String postId, PagingContext pagingContext);

    List<CommentDO> queryCommentComment(String commentId, PagingContext pagingContext);

    CommentDO queryById(String commentId);
}
