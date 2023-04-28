package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.CommentVoteDO;
import id.thesis.shumishumi.foundation.model.result.primarykey.CommentVoteDOPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentVoteRepository extends JpaRepository<CommentVoteDO, CommentVoteDOPK> {
    @Query("SELECT COUNT(cv) FROM CommentVoteDO cv WHERE cv.pk.commentId = :comment_id AND cv.value = :value")
    int countCommentVote(@Param("comment_id") String commentId, @Param("value") int value);

    Page<CommentVoteDO> findByPkUserId(String userId, Pageable pageable);
}
