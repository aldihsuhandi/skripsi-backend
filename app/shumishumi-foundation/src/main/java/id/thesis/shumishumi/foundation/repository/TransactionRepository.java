package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.TransactionDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDO, String> {
    Page<TransactionDO> findByUserId(String userId, Pageable pageable);
}
