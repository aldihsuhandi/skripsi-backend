package id.thesis.shumishumi.common.process.callback;

import id.thesis.shumishumi.common.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.core.result.BaseResult;

public interface ProcessCallback {
    BaseResult initResult();

    void process(ProcessTypeEnum processType, BaseResult result);
}
