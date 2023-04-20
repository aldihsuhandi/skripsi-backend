package id.thesis.shumishumi.service.api;

import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

public class ProcessFacade {

    @Autowired
    private Map<String, BaseProcessor> processors;

    @Autowired
    private Map<String, BaseValidator> validators;

    @Autowired
    private SessionService sessionService;

    public void doProcess(final BaseRequest request, final BaseResult result, final ProcessTypeEnum processType) {
        authenticationAndRefresh(request, processType);

        validators.get(processType.getValidatorName()).validate(request);

        processors.get(processType.getProcessorName()).doProcess(result, request);
    }

    private void authenticationAndRefresh(final BaseRequest request, final ProcessTypeEnum processType) {
        String sessionId = request.getSessionId();
        SessionVO sessionVO = sessionService.query(sessionId);

        if (processType.isNeedAuthentication() && (sessionVO == null || (!sessionVO.isRemembered() && (!sessionVO.isActive()
                || sessionVO.getSessionDt().before(new Date()))))) {
            throw new ShumishumiException("Session Expired",
                    ShumishumiErrorCodeEnum.SESSION_EXPIRED);
        }

        if (sessionVO != null) {
            sessionService.refreshSession(sessionId);
        }
    }

    public void setProcessors(Map<String, BaseProcessor> processors) {
        this.processors = processors;
    }

    public void setValidators(Map<String, BaseValidator> validators) {
        this.validators = validators;
    }
}
