package id.thesis.shumishumi.core.callback;

import id.thesis.shumishumi.common.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.facade.result.BaseResult;

public interface ProcessCallback {
    BaseResult initResult();

    void process(ProcessTypeEnum processType, BaseResult result);
}
