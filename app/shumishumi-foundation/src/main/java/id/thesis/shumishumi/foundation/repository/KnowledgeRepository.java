package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.KnowledgeDO;
import id.thesis.shumishumi.foundation.model.result.primarykey.KnowledgeDOPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeRepository extends JpaRepository<KnowledgeDO, KnowledgeDOPK> {
}
