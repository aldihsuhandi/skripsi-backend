package id.thesis.shumishumi.service.api;

import id.thesis.shumishumi.common.service.DictionaryService;
import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.facade.api.CacheFacade;
import id.thesis.shumishumi.facade.model.context.ResultContext;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.cache.RefreshCacheRequest;
import id.thesis.shumishumi.facade.result.cache.RefreshCacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CacheFacadeImpl extends ProcessFacade implements CacheFacade {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public RefreshCacheResult refresh(RefreshCacheRequest request) {
        RefreshCacheResult result = new RefreshCacheResult();
        ResultContext resultContext = new ResultContext();
        try {
            if (request.isRefreshDictionaryCache()) {
                dictionaryService.refreshCache();
            }

            if (request.isRefreshItemCache()) {
                itemService.clearCache();
                itemService.refreshCache(new ArrayList<>(), true);
            }

            if (request.isRefreshUserCache()) {
                userService.clearCache();
                userService.refreshCache(new ArrayList<>(), true);
            }

            resultContext.setSuccess(true);
            resultContext.setResultCode(ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
            resultContext.setResultMsg(ShumishumiErrorCodeEnum.SUCCESS.getErrorMsg());
        } catch (Exception e) {
            resultContext.setSuccess(false);
            resultContext.setResultCode(ShumishumiErrorCodeEnum.SYSTEM_ERROR.getErrorCode());
            resultContext.setResultMsg(e.getMessage());
        } finally {
            result.setResultContext(resultContext);
        }

        return result;
    }
}
