package id.thesis.shumishumi.core.process.callback;

import id.thesis.shumishumi.common.constant.LogConstant;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.context.ResultContext;
import id.thesis.shumishumi.common.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProcessCallbackSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogConstant.FACADE_LOGGER);

    public static BaseResult process(final ProcessTypeEnum processType, final BaseRequest request, final ProcessCallback callback) {
        BaseResult result = callback.initResult();
        ResultContext resultContext = new ResultContext();
        try {
            LogUtil.info(LOGGER, "facade invoke request", request);
            callback.process(processType, result);

            resultContext.setSuccess(true);
            resultContext.setResultCode(ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
            resultContext.setResultMsg(ShumishumiErrorCodeEnum.SUCCESS.getErrorMsg());
        } catch (RuntimeException e) {
            resultContext.setSuccess(false);
            resultContext.setResultCode(ShumishumiErrorCodeEnum.SYSTEM_ERROR.getErrorCode());
            resultContext.setResultMsg(ShumishumiErrorCodeEnum.SYSTEM_ERROR.getErrorMsg());

            if (e instanceof ShumishumiException) {
                ShumishumiException se = (ShumishumiException) e;
                resultContext.setResultMsg(se.getMessage());
                resultContext.setResultCode(se.getErrorCode().getErrorCode());
            }

            LogUtil.exception(resultContext.getResultMsg(), e);
        } finally {
            result.setSessionId(request.getSessionId());
            result.setResultContext(resultContext);
            LogUtil.info(LOGGER, "facade invoke result", result);
        }


        return result;
    }
}
