package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.TransactionDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetailDO, String> {
    List<TransactionDetailDO> findByTransactionId(String transactionId);
}
