package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.UserCrowdDO;
import id.thesis.shumishumi.foundation.model.result.primarykey.UserCrowdDOPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCrowdRepository extends JpaRepository<UserCrowdDO, UserCrowdDOPK> {
    @Query("SELECT uc FROM UserCrowdDO uc WHERE uc.pk.userId = :user_id")
    Optional<List<UserCrowdDO>> queryByUserId(@Param("user_id") String crowdId);
}