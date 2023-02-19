package id.thesis.shumishumi.bootstrap.configuration;

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
        userService.refreshCache(null, true);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void refreshItemCache() {
        itemService.refreshCache(null, true);
    }
}
