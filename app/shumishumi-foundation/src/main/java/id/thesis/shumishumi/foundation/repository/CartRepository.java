package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.CartDO;
import id.thesis.shumishumi.foundation.model.result.primarykey.CartDOPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartDO, CartDOPK> {
    Page<CartDO> findByPkUserId(String userId, Pageable pageable);
}
