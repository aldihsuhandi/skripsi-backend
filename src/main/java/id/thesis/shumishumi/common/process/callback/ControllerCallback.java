package id.thesis.shumishumi.common.process.callback;

import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.result.BaseResult;

public interface ControllerCallback<T extends BaseResult, R extends BaseRequest> {
    void authCheck(String clientId, String clientSecret);

    R composeRequest();

    T doProcess(R request);
}
