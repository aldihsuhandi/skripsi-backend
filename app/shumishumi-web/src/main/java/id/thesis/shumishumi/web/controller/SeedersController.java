package id.thesis.shumishumi.web.controller;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.model.context.TracerContext;
import id.thesis.shumishumi.foundation.seeders.*;
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
    private UserSeeder userSeeder;

    @Autowired
    private HobbySeeder hobbySeeder;

    @Autowired
    private ItemCategorySeeder itemCategorySeeder;

    @Autowired
    private ItemSeeders itemSeeders;

    @GetMapping("/seeders")
    public String seeders() {
        String result = "seeders success";
        TracerContext.initialize();

        String traceId = TracerContext.getTraceId();

        try {
            composeSeeders();
            executeSeeders();
        } catch (Exception e) {
            LogUtil.exception(e.getMessage(), e);
            result = String.format("seeders failed: %s", e.getMessage());
        }

        TracerContext.removeTracer();
        return traceId + " " + result;
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
        seeders.add(itemSeeders);

        seeders.sort(Comparator.comparingInt(BaseSeeder::getOrder));
    }
}
