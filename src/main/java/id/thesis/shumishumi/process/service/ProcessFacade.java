package id.thesis.shumishumi.process.service;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.core.service.session.SessionService;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.process.processor.BaseProcessor;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class ProcessFacade {

    @Autowired
    private Map<String, BaseProcessor> processors;

    @Autowired
    private Map<String, BaseValidator> validators;

    @Autowired
    private Map<String, Boolean> needAuthentication;

    @Autowired
    private SessionService sessionService;

    public void doProcess(final BaseRequest request, final BaseResult result, final ProcessTypeEnum processType) throws Exception {
        authentication(request, processType);

        validators.get(processType.getValidatorName()).validate(request);

        processors.get(processType.getProcessName()).doProcess(result, request);
    }

    private void authentication(final BaseRequest request, final ProcessTypeEnum processType) throws ShumishumiException {
        if (!needAuthentication.get(processType.getProcessName())) {
            return;
        }
        String sessionId = request.getSessionId();
    }

    public void setProcessors(Map<String, BaseProcessor> processors) {
        this.processors = processors;
    }

    public void setValidators(Map<String, BaseValidator> validators) {
        this.validators = validators;
    }
}
