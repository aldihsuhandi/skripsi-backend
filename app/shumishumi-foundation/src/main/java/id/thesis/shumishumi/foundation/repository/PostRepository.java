package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.PostDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PostRepository extends JpaRepository<PostDO, String>, PostRepositoryCustom {

    @Modifying
    @Transactional
    @Query("UPDATE PostDO po SET po.isDeleted = false WHERE po.postId = :post_id")
    void softDelete(@Param("post_id") String postId);

}
