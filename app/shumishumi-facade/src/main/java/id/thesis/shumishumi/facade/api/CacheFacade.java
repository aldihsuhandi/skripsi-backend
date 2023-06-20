package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.cache.RefreshCacheRequest;
import id.thesis.shumishumi.facade.result.cache.RefreshCacheResult;

public interface CacheFacade {
    RefreshCacheResult refresh(RefreshCacheRequest request);
}
