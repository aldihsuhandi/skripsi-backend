package id.thesis.shumishumi.service.api;

import id.thesis.shumishumi.core.callback.ProcessCallback;
import id.thesis.shumishumi.core.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.facade.api.ReviewFacade;
import id.thesis.shumishumi.facade.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.facade.request.review.ReviewCreateRequest;
import id.thesis.shumishumi.facade.request.review.ReviewDetailRequest;
import id.thesis.shumishumi.facade.request.review.ReviewQueryRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.review.ReviewCreateResult;
import id.thesis.shumishumi.facade.result.review.ReviewDetailResult;
import id.thesis.shumishumi.facade.result.review.ReviewQueryResult;
import org.springframework.stereotype.Service;

@Service
public class ReviewFacadeImpl extends ProcessFacade implements ReviewFacade {
    @Override
    public ReviewCreateResult create(ReviewCreateRequest request) {
        return (ReviewCreateResult) ProcessCallbackSupport.process(ProcessTypeEnum.REVIEW_CREATE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new ReviewCreateResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public ReviewQueryResult query(ReviewQueryRequest request) {
        return (ReviewQueryResult) ProcessCallbackSupport.process(ProcessTypeEnum.REVIEW_QUERY, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new ReviewQueryResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public ReviewDetailResult detail(ReviewDetailRequest request) {
        return (ReviewDetailResult) ProcessCallbackSupport.process(ProcessTypeEnum.REVIEW_DETAIL, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new ReviewDetailResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }
}
