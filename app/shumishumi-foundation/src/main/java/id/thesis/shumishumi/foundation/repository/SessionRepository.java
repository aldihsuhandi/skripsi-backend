package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.SessionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface SessionRepository extends JpaRepository<SessionDO, String> {
    @Modifying
    @Transactional
    @Query("UPDATE SessionDO s SET s.isActive = false, s.isRemembered = false WHERE s.sessionId = :id")
    void logout(@Param("id") String sessionId);

    @Modifying
    @Transactional
    @Query("UPDATE SessionDO s SET s.sessionDt = :dt WHERE s.sessionId = :id")
    void refreshSession(@Param("id") String sessionId, @Param("dt") Date sessionDt);

    @Modifying
    @Transactional
    @Query(value = "UPDATE SessionDO s SET s.isActive = false WHERE s.sessionDt < CURRENT_TIMESTAMP() AND s.isActive = true AND s.isRemembered = false")
    void expiredSession();
}
