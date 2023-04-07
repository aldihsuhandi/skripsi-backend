/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.process.callback;

import id.thesis.shumishumi.common.constant.LogConstant;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.context.Headers;
import id.thesis.shumishumi.common.model.context.ResultContext;
import id.thesis.shumishumi.common.model.context.TracerContext;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ControllerCallbackSupport.java, v 0.1 2022‐12‐26 3:50 PM Aldih Suhandi Exp $$
 */
public class ControllerCallbackSupport {

    private static final Logger LOGGER = LoggerFactory.
            getLogger(LogConstant.CONTROLLER_LOGGER);


    public static BaseResult process(final Headers headers, final BaseRequest baseRequest, final ControllerCallback controllerCallback) {
        try {
            TracerContext.initialize();

            LogUtil.info(LOGGER, String.format("controller invoke request[headers=%s,body=%s]"
                    , headers, baseRequest));
            controllerCallback.authCheck();

            composeSessionId(headers, baseRequest);

            BaseResult baseResult = controllerCallback.doProcess();
            baseResult.setTraceId(TracerContext.getTraceId());

            LogUtil.info(LOGGER, String.format("controller invoke result[result=%s]"
                    , baseResult));

            return baseResult;
        } catch (ShumishumiException e) {
            BaseResult baseResult = controllerCallback.initResult();
            ResultContext resultContext = new ResultContext();
            resultContext.setSuccess(false);
            resultContext.setResultMsg(e.getMessage());
            resultContext.setResultCode(e.getErrorCode().getErrorCode());

            LogUtil.exception(resultContext.getResultMsg(), e);

            baseResult.setResultContext(resultContext);
            baseResult.setTraceId(TracerContext.getTraceId());

            LogUtil.info(LOGGER, String.format("controller invoke result[result=%s]"
                    , baseResult));

            return baseResult;
        } finally {
            TracerContext.removeTracer();
        }
    }

    private static void composeSessionId(Headers headers, BaseRequest baseRequest) {
        if (headers.getSessionId() == null || headers.getSessionId().isEmpty()) {
            return;
        }

        baseRequest.setSessionId(headers.getSessionId());
    }
}
