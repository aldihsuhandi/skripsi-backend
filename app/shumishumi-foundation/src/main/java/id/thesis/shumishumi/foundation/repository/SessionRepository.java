package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.SessionDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<SessionDO, String> {
}
