package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.ImageDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageDO, String> {
}
