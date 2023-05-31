package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.review.ReviewCreateRequest;
import id.thesis.shumishumi.facade.result.review.ReviewCreateResult;

public interface ReviewFacade {
    ReviewCreateResult create(ReviewCreateRequest request);
}
