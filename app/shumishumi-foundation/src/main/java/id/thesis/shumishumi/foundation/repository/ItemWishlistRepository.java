package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.ItemWishlistDO;
import id.thesis.shumishumi.foundation.model.result.primarykey.ItemWishlistDOPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemWishlistRepository extends JpaRepository<ItemWishlistDO, ItemWishlistDOPK> {
    @Query("SELECT iw FROM ItemWishlistDO iw WHERE iw.pk.userId = :user_id")
    List<ItemWishlistDO> findByUserId(@Param("user_id") String userId);

    @Query("SELECT COUNT(iw) FROM ItemWishlistDO iw WHERE iw.pk.itemId = :item_id")
    int countByItemId(@Param("item_id") String itemId);
}
