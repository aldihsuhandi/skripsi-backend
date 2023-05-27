package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.TransactionDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDO, String> {
    Page<TransactionDO> findByUserId(String userId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE TransactionDO t SET t.status = :status WHERE t.transactionId = :id")
    void updateStatusById(@Param("id") String transactionId, @Param("status") String transactionStatus);

    @Query("SELECT t FROM TransactionDO t WHERE t.userId = :user_id AND t.status NOT LIKE '%INIT%' AND t.status LIKE %:status%")
    Page<TransactionDO> queryByStatus(@Param("user_id") String userId, @Param("status") String status, Pageable pageable);

    List<TransactionDO> findByStatus(String status);
}
