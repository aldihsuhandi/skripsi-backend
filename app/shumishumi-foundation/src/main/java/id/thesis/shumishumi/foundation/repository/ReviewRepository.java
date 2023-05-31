package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.ReviewDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewDO, String> {
    Page<ReviewDO> findByMerchantIdAndNeedReview(String merchantId, boolean needReview, Pageable pageable);

    Page<ReviewDO> findByUserIdAndNeedReview(String userId, boolean needReview, Pageable pageable);
}
