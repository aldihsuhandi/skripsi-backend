package id.thesis.shumishumi.process.processor;


import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.result.BaseResult;

public interface BaseProcessor {
    void doProcess(final BaseResult result, final BaseRequest request) throws ShumishumiException;
}
