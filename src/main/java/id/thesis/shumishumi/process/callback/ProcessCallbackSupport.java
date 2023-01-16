package id.thesis.shumishumi.process.callback;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.context.ResultContext;
import id.thesis.shumishumi.common.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.result.BaseResult;

public class ProcessCallbackSupport {
    public static BaseResult process(final ProcessTypeEnum processType, final BaseRequest request, final ProcessCallback callback) {
        BaseResult result = callback.initResult();
        ResultContext resultContext = new ResultContext();
        try {
            callback.process(processType, result);

            resultContext.setSuccess(true);
            resultContext.setResultCode(ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
            resultContext.setResultMsg(ShumishumiErrorCodeEnum.SUCCESS.getErrorMsg());
        } catch (RuntimeException e) {
            e.printStackTrace();
            resultContext.setSuccess(false);
            resultContext.setResultCode(ShumishumiErrorCodeEnum.SYSTEM_ERROR.getErrorCode());
            resultContext.setResultMsg(ShumishumiErrorCodeEnum.SYSTEM_ERROR.getErrorMsg());

            if (e instanceof ShumishumiException) {
                ShumishumiException se = (ShumishumiException) e;
                resultContext.setResultMsg(se.getMessage());
                resultContext.setResultCode(se.getErrorCode().getErrorCode());
            }
        }

        result.setResultContext(resultContext);

        return result;
    }
}
