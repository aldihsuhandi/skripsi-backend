package id.thesis.shumishumi.process.callback;

import id.thesis.shumishumi.common.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.rest.result.BaseResult;

public interface ProcessCallback {
    BaseResult initResult();

    void process(ProcessTypeEnum processType, BaseResult result) throws Exception;
}
