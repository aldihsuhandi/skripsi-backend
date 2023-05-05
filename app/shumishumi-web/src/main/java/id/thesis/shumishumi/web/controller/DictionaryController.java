package id.thesis.shumishumi.web.controller;

import id.thesis.shumishumi.common.model.form.dictionary.DictionaryQueryForm;
import id.thesis.shumishumi.core.callback.ControllerCallback;
import id.thesis.shumishumi.core.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.facade.api.DictionaryFacade;
import id.thesis.shumishumi.facade.request.dictionary.DictionaryQueryRequest;
import id.thesis.shumishumi.facade.result.dictionary.DictionaryQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController extends BaseController {

    @Autowired
    private DictionaryFacade dictionaryFacade;

    @PostMapping("/query")
    public ResponseEntity<DictionaryQueryResult> query(@RequestHeader HttpHeaders headers, @RequestBody DictionaryQueryForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<DictionaryQueryResult, DictionaryQueryRequest>() {

            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public DictionaryQueryRequest composeRequest() {
                DictionaryQueryRequest request = new DictionaryQueryRequest();
                request.setDictionaryKey(form.getDictionaryKey());

                return request;
            }

            @Override
            public DictionaryQueryResult doProcess(DictionaryQueryRequest request) {
                return dictionaryFacade.queryDict(request);
            }
        });
    }
}
