package id.thesis.shumishumi.core.configuration;

import id.thesis.shumishumi.common.model.context.TracerContext;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.core.service.ItemService;
import id.thesis.shumishumi.core.service.UserService;
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
}
