package id.thesis.shumishumi.web.controller;

import id.thesis.shumishumi.common.model.form.review.ReviewCreateForm;
import id.thesis.shumishumi.common.model.form.review.ReviewDetailForm;
import id.thesis.shumishumi.common.model.form.review.ReviewQueryForm;
import id.thesis.shumishumi.core.callback.ControllerCallback;
import id.thesis.shumishumi.core.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.facade.api.ReviewFacade;
import id.thesis.shumishumi.facade.request.review.ReviewCreateRequest;
import id.thesis.shumishumi.facade.request.review.ReviewDetailRequest;
import id.thesis.shumishumi.facade.request.review.ReviewQueryRequest;
import id.thesis.shumishumi.facade.result.review.ReviewCreateResult;
import id.thesis.shumishumi.facade.result.review.ReviewDetailResult;
import id.thesis.shumishumi.facade.result.review.ReviewQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController extends BaseController {
    @Autowired
    private ReviewFacade reviewFacade;

    @PostMapping("/query")
    public ResponseEntity<ReviewQueryResult> query(@RequestHeader HttpHeaders headers, @RequestBody ReviewQueryForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON,
                new ControllerCallback<ReviewQueryResult, ReviewQueryRequest>() {
                    @Override
                    public void authCheck(String clientId, String clientSecret) {
                        authenticate(clientId, clientSecret);
                    }

                    @Override
                    public ReviewQueryRequest composeRequest() {
                        ReviewQueryRequest request = new ReviewQueryRequest();
                        request.setPageNumber(form.getPageNumber());
                        request.setNumberOfItem(form.getNumberOfItem());
                        request.setNeedReview(form.isNeedReview());
                        request.setType(form.getType());

                        return request;
                    }

                    @Override
                    public ReviewQueryResult doProcess(ReviewQueryRequest request) {
                        return reviewFacade.query(request);
                    }
                });
    }

    @PostMapping("/detail")
    public ResponseEntity<ReviewDetailResult> detail(@RequestHeader HttpHeaders headers, @RequestBody ReviewDetailForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON,
                new ControllerCallback<ReviewDetailResult, ReviewDetailRequest>() {
                    @Override
                    public void authCheck(String clientId, String clientSecret) {
                        authenticate(clientId, clientSecret);
                    }

                    @Override
                    public ReviewDetailRequest composeRequest() {
                        ReviewDetailRequest request = new ReviewDetailRequest();
                        request.setReviewId(request.getReviewId());

                        return request;
                    }

                    @Override
                    public ReviewDetailResult doProcess(ReviewDetailRequest request) {
                        return reviewFacade.detail(request);
                    }
                });
    }

    @PostMapping("/create")
    public ResponseEntity<ReviewCreateResult> create(@RequestHeader HttpHeaders headers, @RequestBody ReviewCreateForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON,
                new ControllerCallback<ReviewCreateResult, ReviewCreateRequest>() {
                    @Override
                    public void authCheck(String clientId, String clientSecret) {
                        authenticate(clientId, clientSecret);
                    }

                    @Override
                    public ReviewCreateRequest composeRequest() {
                        ReviewCreateRequest request = new ReviewCreateRequest();
                        request.setReviewId(form.getReviewId());
                        request.setReview(form.getReview());
                        request.setDescription(form.getDescription());
                        request.setImages(form.getImages());
                        request.setInterestLevel(form.getInterestLevel());

                        return request;
                    }

                    @Override
                    public ReviewCreateResult doProcess(ReviewCreateRequest request) {
                        return reviewFacade.create(request);
                    }
                });
    }
}
