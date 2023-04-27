package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.facade.model.viewobject.CommentVO;

import java.util.List;

public interface CommentService {
    String create(String userId, String content, String replyId, String replyType);

    void update(String commentId, String content);

    void delete(String commentId);

    List<CommentVO> query(String replyId, String replyType);

    CommentVO queryById(String commentId);
}
