package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.dictionary.DictionaryQueryRequest;
import id.thesis.shumishumi.facade.result.dictionary.DictionaryQueryResult;

public interface DictionaryFacade {
    DictionaryQueryResult queryDict(DictionaryQueryRequest request);
}
