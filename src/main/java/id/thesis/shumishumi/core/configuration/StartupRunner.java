package id.thesis.shumishumi.core.configuration;

import id.thesis.shumishumi.common.model.context.TracerContext;
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
        TracerContext.initialize();
        userService.refreshCache(null, true);
        TracerContext.removeTracer();
    }

    //    @EventListener(ApplicationReadyEvent.class)
    public void refreshItemCache() {
        TracerContext.initialize();
        itemService.refreshCache(null, true);
        TracerContext.removeTracer();
    }
}
