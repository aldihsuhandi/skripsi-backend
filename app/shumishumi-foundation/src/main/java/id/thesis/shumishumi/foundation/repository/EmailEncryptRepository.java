package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.EmailEncryptDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailEncryptRepository extends JpaRepository<EmailEncryptDO, String> {
}
