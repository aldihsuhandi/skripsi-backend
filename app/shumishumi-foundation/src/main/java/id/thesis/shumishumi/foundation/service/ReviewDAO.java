package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.foundation.model.result.ReviewDO;

import java.util.List;

public interface ReviewDAO {
    List<ReviewDO> queryByMerchant(String merchantId, PagingContext pagingContext);

    List<ReviewDO> queryByUserIdAndNeedReview(String userId, boolean needReview, PagingContext pagingContext);

    List<ReviewDO> queryByItemId(String itemId);

    List<ReviewDO> queryByMerchantId(String merchantId);

    ReviewDO queryById(String reviewId);

    void create(ReviewDO reviewDO);

    void update(ReviewDO reviewDO);
}
