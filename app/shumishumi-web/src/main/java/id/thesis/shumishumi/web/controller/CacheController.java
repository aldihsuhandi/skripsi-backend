package id.thesis.shumishumi.web.controller;

import id.thesis.shumishumi.common.model.form.cache.RefreshCacheForm;
import id.thesis.shumishumi.core.callback.ControllerCallback;
import id.thesis.shumishumi.core.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.facade.api.CacheFacade;
import id.thesis.shumishumi.facade.request.cache.RefreshCacheRequest;
import id.thesis.shumishumi.facade.result.cache.RefreshCacheResult;
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
@RequestMapping("/cache")
public class CacheController extends BaseController {

    @Autowired
    private CacheFacade cacheFacade;

    @PostMapping("/refresh")
    public ResponseEntity<RefreshCacheResult> refreshCache(@RequestHeader HttpHeaders headers, @RequestBody RefreshCacheForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<RefreshCacheResult, RefreshCacheRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public RefreshCacheRequest composeRequest() {
                RefreshCacheRequest request = new RefreshCacheRequest();
                request.setRefreshDictionaryCache(form.isRefreshDictionaryCache());
                request.setRefreshUserCache(form.isRefreshUserCache());
                request.setRefreshItemCache(form.isRefreshItemCache());

                return request;
            }

            @Override
            public RefreshCacheResult doProcess(RefreshCacheRequest request) {
                return cacheFacade.refresh(request);
            }
        });
    }
}
