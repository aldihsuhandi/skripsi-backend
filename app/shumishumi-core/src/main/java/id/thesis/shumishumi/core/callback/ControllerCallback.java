package id.thesis.shumishumi.core.callback;


import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.result.BaseResult;

public interface ControllerCallback<T extends BaseResult, R extends BaseRequest> {
    void authCheck(String clientId, String clientSecret);

    R composeRequest();

    T doProcess(R request);
}
