package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.PostVoteDO;
import id.thesis.shumishumi.foundation.model.result.primarykey.PostVoteDOPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostVoteRepository extends JpaRepository<PostVoteDO, PostVoteDOPK> {

    @Query("SELECT COUNT(pv) FROM PostVoteDO pv WHERE pv.pk.postId = :post_id AND pv.value = :value")
    int countPostVote(@Param("post_id") String postId, @Param("value") int value);

}
