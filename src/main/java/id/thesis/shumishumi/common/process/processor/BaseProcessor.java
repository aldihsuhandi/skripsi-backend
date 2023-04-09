package id.thesis.shumishumi.common.process.processor;


import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.result.BaseResult;

public interface BaseProcessor {
    void doProcess(final BaseResult baseResult, final BaseRequest baseRequest);
}
