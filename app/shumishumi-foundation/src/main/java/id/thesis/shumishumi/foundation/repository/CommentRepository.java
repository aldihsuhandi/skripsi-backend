package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.CommentDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<CommentDO, String> {
    Page<CommentDO> findByReplyCommentId(String replyCommentId, Pageable pageable);

    Page<CommentDO> findByReplyPostId(String replyPostId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE CommentDO c SET c.content = :content WHERE c.commentId = :id")
    void updateComment(@Param("id") String commentId, @Param("content") String content);

    @Modifying
    @Transactional
    @Query("UPDATE CommentDO c SET c.isDeleted = true WHERE c.commentId = :id")
    void softDelete(@Param("id") String commentId);
}
