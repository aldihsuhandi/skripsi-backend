package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.ActivityDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityDO, String> {
    List<ActivityDO> findByUserId(String userId);
}
