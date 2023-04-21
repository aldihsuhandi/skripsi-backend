package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.ContentDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<ContentDO, String> {
}