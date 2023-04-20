package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.ClientDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientDO, String> {
}
