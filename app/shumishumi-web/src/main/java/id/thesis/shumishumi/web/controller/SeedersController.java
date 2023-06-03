package id.thesis.shumishumi.web.controller;

import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.model.context.TracerContext;
import id.thesis.shumishumi.foundation.seeders.BaseSeeder;
import id.thesis.shumishumi.foundation.seeders.HobbySeeder;
import id.thesis.shumishumi.foundation.seeders.ImageSeeder;
import id.thesis.shumishumi.foundation.seeders.ItemCategorySeeder;
import id.thesis.shumishumi.foundation.seeders.ItemSeeder;
import id.thesis.shumishumi.foundation.seeders.KnowledgeSeeder;
import id.thesis.shumishumi.foundation.seeders.PostSeeder;
import id.thesis.shumishumi.foundation.seeders.UserSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class SeedersController {

    private final List<BaseSeeder> seeders = new ArrayList<>();

    @Autowired
    private ImageSeeder imageSeeder;

    @Autowired
    private UserSeeder userSeeder;

    @Autowired
    private HobbySeeder hobbySeeder;

    @Autowired
    private ItemCategorySeeder itemCategorySeeder;

    @Autowired
    private ItemSeeder itemSeeder;

    @Autowired
    private PostSeeder postSeeder;

    @Autowired
    private KnowledgeSeeder knowledgeSeeder;

    @GetMapping("/seeders")
    public String seeders() {
        String result = "seeders success";
        TracerContext.initialize();

        String traceId = TracerContext.getTraceId();

        try {
            composeSeeders();
            executeSeeders();
            refreshCache();
        } catch (Exception e) {
            LogUtil.exception(e.getMessage(), e);
            result = String.format("seeders failed: %s", e.getMessage());
        }

        TracerContext.removeTracer();
        return traceId + " " + result;
    }

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    private void refreshCache() {
        userService.clearCache();
        userService.refreshCache(new ArrayList<>(), true);
        itemService.clearCache();
        itemService.refreshCache(new ArrayList<>(), true);
    }

    private void executeSeeders() {
        for (int i = seeders.size() - 1; i >= 0; --i) {
            BaseSeeder seeder = seeders.get(i);
            seeder.delete();
        }

        seeders.forEach(BaseSeeder::startSeeding);
    }

    private void composeSeeders() {
        seeders.add(userSeeder);
        seeders.add(hobbySeeder);
        seeders.add(itemCategorySeeder);
        seeders.add(itemSeeder);
        seeders.add(imageSeeder);
        seeders.add(postSeeder);
        seeders.add(knowledgeSeeder);


        seeders.sort(Comparator.comparingInt(BaseSeeder::getOrder));
    }
}
