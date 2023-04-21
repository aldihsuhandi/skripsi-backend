package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.ItemCrowdDO;
import id.thesis.shumishumi.foundation.model.result.primarykey.ItemCrowdDOPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCrowdRepository extends JpaRepository<ItemCrowdDO, ItemCrowdDOPK> {
    @Query("SELECT ic FROM ItemCrowdDO ic WHERE ic.pk.crowdId = :crowd_id")
    List<ItemCrowdDO> queryByCrowdId(@Param("crowd_id") String crowdId);
}
