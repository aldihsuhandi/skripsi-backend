package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.ItemDO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ItemRepository extends JpaRepository<ItemDO, String>, ItemRepositoryCustom {
    Page<ItemDO> findByItemPriceBetween(Example<ItemDO> example, Long minItemPrice, Long maxItemPrice, Pageable pageable);

    Page<ItemDO> findByItemPriceGreaterThanEqual(Example<ItemDO> example, Long minItemPrice, Pageable pageable);

    Page<ItemDO> findByItemPriceLessThanEqual(Example<ItemDO> example, Long maxItemPrice, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE ItemDO i SET i.itemImages = :images WHERE i.itemId = :item_id")
    void updateImageByItemId(@Param("item_id") String itemId, @Param("images") String images);

    @Modifying
    @Transactional
    @Query("UPDATE ItemDO i set i.isApproved = true WHERE i.itemId = :item_id")
    void approve(@Param("item_id") String itemId);

    @Modifying
    @Transactional
    @Query("UPDATE ItemDO i set i.userLevelId = :level_id WHERE i.itemId = :item_id")
    void updateUserLevel(@Param("item_id") String itemId, @Param("level_id") String levelId);

    @Modifying
    @Transactional
    @Query("UPDATE ItemDO i SET i.review = :review WHERE i.itemId = :id")
    void updateReview(@Param("id") String itemId, @Param("review") double review);
}
