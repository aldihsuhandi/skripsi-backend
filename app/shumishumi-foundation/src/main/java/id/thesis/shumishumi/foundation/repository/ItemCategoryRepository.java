package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.ItemCategoryDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategoryDO, String> {
    Optional<ItemCategoryDO> findByCategoryName(String categoryName);
}
