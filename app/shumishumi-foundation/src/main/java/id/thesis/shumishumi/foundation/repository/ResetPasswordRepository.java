package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.ResetPasswordDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ResetPasswordRepository extends JpaRepository<ResetPasswordDO, String> {
    @Modifying
    @Transactional
    @Query("UPDATE ResetPasswordDO rp SET rp.isActive = false WHERE rp.uuid = :uuid")
    void invalidate(@Param("uuid") String uuid);

    @Query("SELECT rp FROM ResetPasswordDO rp WHERE rp.isActive = true AND rp.expiredTime > CURRENT_TIMESTAMP() AND rp.uuid = :uuid")
    Optional<ResetPasswordDO> findActiveRequest(@Param("uuid") String uuid);
}
