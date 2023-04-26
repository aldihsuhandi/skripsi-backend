package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.CommentDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentDO, String> {
    List<CommentDO> findByReplyCommentId(String replyCommentId);

    List<CommentDO> findByReplyPostId(String replyPostId);

    @Modifying
    @Transactional
    @Query("UPDATE CommentDO c SET c.content = :content WHERE c.commentId = :id")
    void updateComment(@Param("id") String commentId, @Param("content") String content);

    @Modifying
    @Transactional
    @Query("UPDATE CommentDO c SET c.isDeleted = true WHERE c.commentId = :id")
    void softDelete(@Param("id") String commentId);
}
