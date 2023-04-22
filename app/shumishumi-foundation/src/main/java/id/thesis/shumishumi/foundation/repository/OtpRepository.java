package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.OtpDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OtpRepository extends JpaRepository<OtpDO, String> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE OtpDO o SET o.isActive = false WHERE o.otpId = :otp_id", nativeQuery = true)
    void deactivate(@Param("otp_id") String otpId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE OtpDO o SET o.isActive = false WHERE o.otpDt <= CURRENT_TIMESTAMP() AND o.isActive = true", nativeQuery = true)
    void deactivate();
}
