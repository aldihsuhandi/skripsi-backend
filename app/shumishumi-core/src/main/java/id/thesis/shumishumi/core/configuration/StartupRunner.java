package id.thesis.shumishumi.core.configuration;

import id.thesis.shumishumi.common.service.DictionaryService;
import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.model.context.TracerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private DictionaryService dictionaryService;

    @EventListener(ApplicationReadyEvent.class)
    public void refreshUserCache() {
        try {
            TracerContext.initialize();
            userService.refreshCache(null, true);
        } catch (Exception e) {
            LogUtil.exception(e.getMessage(), e);
        } finally {
            TracerContext.removeTracer();
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void refreshItemCache() {
        try {
            TracerContext.initialize();
            itemService.refreshCache(null, true);
        } catch (Exception e) {
            LogUtil.exception(e.getMessage(), e);
        } finally {
            TracerContext.removeTracer();
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void refreshDictionaryCache() {
        try {
            TracerContext.initialize();
            dictionaryService.refreshCache();
        } catch (Exception e) {
            LogUtil.exception(e.getMessage(), e);
        } finally {
            TracerContext.removeTracer();
        }
    }
}
