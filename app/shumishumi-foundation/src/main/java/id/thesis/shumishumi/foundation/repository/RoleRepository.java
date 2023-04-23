package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleDO, String> {
}
