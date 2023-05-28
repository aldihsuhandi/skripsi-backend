package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.HistoryItemDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryItemRepository extends JpaRepository<HistoryItemDO, String> {
}
