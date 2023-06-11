package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.review.ReviewCreateRequest;
import id.thesis.shumishumi.facade.request.review.ReviewDetailRequest;
import id.thesis.shumishumi.facade.request.review.ReviewQueryRequest;
import id.thesis.shumishumi.facade.result.review.ReviewCreateResult;
import id.thesis.shumishumi.facade.result.review.ReviewDetailResult;
import id.thesis.shumishumi.facade.result.review.ReviewQueryResult;

public interface ReviewFacade {
    ReviewCreateResult create(ReviewCreateRequest request);

    ReviewQueryResult query(ReviewQueryRequest request);

    ReviewDetailResult detail(ReviewDetailRequest request);
}
