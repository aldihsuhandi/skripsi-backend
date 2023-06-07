package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.CartDO;
import id.thesis.shumishumi.foundation.model.result.primarykey.CartDOPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartDO, CartDOPK> {
    Page<CartDO> findByPkUserId(String userId, Pageable pageable);

    List<CartDO> findByPkUserId(String userId);

    List<CartDO> findByPkUserIdAndSelected(String userId, boolean selected);

    @Modifying
    @Transactional
    @Query("UPDATE CartDO c SET c.selected = :selected WHERE c.pk.userId = :user AND c.pk.itemId IN :items")
    void updateSelectedCart(@Param("items") List<String> itemIds,
                            @Param("user") String userId, @Param("selected") boolean selected);
}
