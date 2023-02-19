/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.web.callback;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.context.Headers;
import id.thesis.shumishumi.common.model.context.ResultContext;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.result.BaseResult;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ControllerCallbackSupport.java, v 0.1 2022‐12‐26 3:50 PM Aldih Suhandi Exp $$
 */
public class ControllerCallbackSupport {
    public static BaseResult process(final Headers headers, final BaseRequest baseRequest, final ControllerCallback controllerCallback) {
        try {
            controllerCallback.authCheck();

            composeSessionId(headers, baseRequest);

            return controllerCallback.doProcess();
        } catch (ShumishumiException e) {
            e.printStackTrace();
            BaseResult baseResult = controllerCallback.initResult();
            ResultContext resultContext = new ResultContext();
            resultContext.setSuccess(false);
            resultContext.setResultMsg(e.getMessage());
            resultContext.setResultCode(e.getErrorCode().getErrorCode());

            baseResult.setResultContext(resultContext);
            return baseResult;
        }
    }

    private static void composeSessionId(Headers headers, BaseRequest baseRequest) {
        if (headers.getSessionId() == null || headers.getSessionId().isEmpty()) {
            return;
        }

        baseRequest.setSessionId(headers.getSessionId());
    }
}
