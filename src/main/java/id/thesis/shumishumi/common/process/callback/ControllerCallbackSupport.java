package id.thesis.shumishumi.common.process.callback;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.context.ResultContext;
import id.thesis.shumishumi.common.model.context.TracerContext;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.common.util.constant.LogConstant;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.rest.form.BaseForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


public class ControllerCallbackSupport {
    private static final Logger LOGGER = LoggerFactory.
            getLogger(LogConstant.CONTROLLER_LOGGER);

    private static final String TRACE_ID = "tracerId";
    private static final String SESSION_ID = "sessionId";
    private static final String CLIENT_ID = "clientId";
    private static final String CLIENT_SECRET = "clientSecret";
    private static final String CONTENT_TYPE = "Content-Type";

    public static <T extends BaseResult, R extends BaseRequest>
    ResponseEntity<T> process(HttpHeaders httpHeaders, BaseForm baseForm, ControllerCallback<T, R> callback) {
        TracerContext.initialize();

        LogUtil.info(LOGGER, String.format("controller invoke request[form=%s]", baseForm));

        String sessionId = getSessionFromHeaders(httpHeaders);
        HttpHeaders headers = new HttpHeaders();
        try {
            String clientId = getValueFromHeaders(httpHeaders, CLIENT_ID);
            String clientSecret = getValueFromHeaders(httpHeaders, CLIENT_SECRET);
            callback.authCheck(clientId, clientSecret);

            R request = callback.composeRequest();
            populateRequest(httpHeaders, request);

            headers.set(TRACE_ID, TracerContext.getTraceId());
            headers.set(SESSION_ID, sessionId);
            headers.setContentType(MediaType.APPLICATION_JSON);

            T result = callback.doProcess(request);

            ResponseEntity<T> response = ResponseEntity.ok()
                    .headers(headers)
                    .body(result);
            LogUtil.info(LOGGER, String.format("controller invoke response[response=%s]", response.toString()));

            return response;
        } catch (ShumishumiException e) {
            BaseResult baseResult = new BaseResult();
            ResultContext resultContext = new ResultContext();
            resultContext.setSuccess(false);
            resultContext.setResultCode(e.getMessage());
            resultContext.setResultMsg(e.getErrorCode().getErrorMsg());

            headers.set(TRACE_ID, TracerContext.getTraceId());
            headers.set(SESSION_ID, sessionId);
            headers.setContentType(MediaType.APPLICATION_JSON);

            LogUtil.exception(resultContext.getResultMsg(), e);

            baseResult.setResultContext(resultContext);

            ResponseEntity<T> response = ResponseEntity.ok()
                    .headers(headers)
                    .body((T) baseResult);

            LogUtil.info(LOGGER, String.format("controller invoke response[response=%s]", response.toString()));

            return response;
        } finally {
            TracerContext.removeTracer();
        }

    }

    private static void populateRequest(HttpHeaders httpHeaders, BaseRequest request) {
        request.setSessionId(getSessionFromHeaders(httpHeaders));
    }

    private static String getSessionFromHeaders(HttpHeaders httpHeaders) {
        if (httpHeaders == null || httpHeaders.getFirst(SESSION_ID) == null) {
            return "";
        }

        return httpHeaders.getFirst(SESSION_ID);
    }

    private static String getValueFromHeaders(HttpHeaders httpHeaders, String key) {
        if (httpHeaders == null || httpHeaders.getFirst(key) == null) {
            return "";
        }

        return httpHeaders.getFirst(key);
    }
}
