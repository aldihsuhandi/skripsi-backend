package id.thesis.shumishumi.service.api;

import id.thesis.shumishumi.core.callback.ProcessCallback;
import id.thesis.shumishumi.core.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.facade.api.DictionaryFacade;
import id.thesis.shumishumi.facade.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.facade.request.dictionary.DictionaryQueryRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.dictionary.DictionaryQueryResult;
import org.springframework.stereotype.Service;

@Service
public class DictionaryFacadeImpl extends ProcessFacade implements DictionaryFacade {
    @Override
    public DictionaryQueryResult queryDict(DictionaryQueryRequest request) {
        return (DictionaryQueryResult) ProcessCallbackSupport.process(ProcessTypeEnum.DICTIONARY_QUERY, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new DictionaryQueryResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }
}
