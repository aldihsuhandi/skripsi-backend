package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.viewobject.ReviewVO;

import java.util.List;

public interface ReviewService {
    void create(ReviewVO review);

    void update(ReviewVO review);

    ReviewVO queryById(String id);

    List<ReviewVO> queryByMerchant(String merchantId, PagingContext pagingContext);

    List<ReviewVO> queryByUserAndNeedReview(String userId, boolean needReview, PagingContext pagingContext);
}
