package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.KnowledgeDO;
import id.thesis.shumishumi.foundation.model.result.primarykey.KnowledgeDOPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface KnowledgeRepository extends JpaRepository<KnowledgeDO, KnowledgeDOPK> {
    @Modifying
    @Transactional
    @Query("UPDATE KnowledgeDO k SET k.knowledge = :knowledge WHERE k.pk.knowledgeKey = :key AND k.pk.knowledgeType = :type")
    void updateKnowledgeValue(@Param("type") String type, @Param("key") String key, @Param("knowledge") String knowledge);
}
