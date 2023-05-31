package id.thesis.shumishumi.service.api;

import id.thesis.shumishumi.core.callback.ProcessCallback;
import id.thesis.shumishumi.core.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.facade.api.ReviewFacade;
import id.thesis.shumishumi.facade.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.facade.request.review.ReviewCreateRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.review.ReviewCreateResult;
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
}
