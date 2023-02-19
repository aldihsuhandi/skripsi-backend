package id.thesis.shumishumi.core.processor;


import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.result.BaseResult;

public interface BaseProcessor {
    void doProcess(final BaseResult result, final BaseRequest request);
}
