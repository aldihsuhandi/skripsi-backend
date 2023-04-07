package id.thesis.shumishumi.foundation.seeders;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.core.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import java.util.ArrayList;
import java.util.List;

@Priority(1)
@Component
@Profile("!test")
public class ItemCategorySeeders extends BaseSeeders {
    @Override
    String setTableName() {
        return DatabaseConst.TABLE_ITEM_CATEGORIES;
    }

    @Override
    String setSeedersName() {
        return "itemCategorySeeders";
    }

    @Autowired
    private ItemCategoryService itemCategoryService;

    @Override
    void seeds() {
        List<String> categories = new ArrayList<>();
        // keyboard categories
        categories.add("Prebuilt Keyboard");
        categories.add("Keyboard Switch");
        categories.add("Keyboard Keycaps");
        categories.add("Keyboard Stabilizer");
        categories.add("Keyboard Modding Essential");

        // computer categories
        categories.add("CPU");
        categories.add("GPU");
        categories.add("RAM");
        categories.add("Power Supply");
        categories.add("CPU Cooler");
        categories.add("Computer Case");
        categories.add("Custom Computer Cable");
        categories.add("Motherboard");

        categories.forEach(category -> {
            try {
                itemCategoryService.create(category);
                LogUtil.info(LOGGER, String.format("itemCategorySeeders: successfully create category %s", category));
            } catch (Exception e) {
                LogUtil.info(LOGGER, String.format("itemCategorySeeders: encountered an error %s", e.getMessage()));
            }
        });
    }

    @Override
    @PostConstruct
    public void execute() {
        super.execute();
    }
}
