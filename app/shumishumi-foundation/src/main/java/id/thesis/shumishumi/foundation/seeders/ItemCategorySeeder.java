package id.thesis.shumishumi.foundation.seeders;

import id.thesis.shumishumi.common.model.datastructure.Pair;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.ItemCategoryDO;
import id.thesis.shumishumi.foundation.repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCategorySeeder extends BaseSeeder {

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    void setOrder() {
        this.order = 1;
    }

    @Override
    void setSeederName() {
        this.seederName = "item category seeder";
    }

    @Override
    void deleteRecords() {
        itemCategoryRepository.deleteAll();
    }

    @Override
    void seed() {
        List<Pair<String, String>> categories = new ArrayList<>();
        // MUSIC
        categories.add(new Pair<>("ACOUSTIC_GUITAR", "Acoustic Guitar"));
        categories.add(new Pair<>("ELECTRIC_GUITAR", "Electric Guitar"));

        // COMPUTER
        categories.add(new Pair<>("CPU", "Central Processing Unit"));
        categories.add(new Pair<>("GPU", "Graphics Processing Unit"));
        categories.add(new Pair<>("RAM", "Random Access Memory"));
        categories.add(new Pair<>("MOTHERBOARD", "Motherboard"));

        // KEYBOARD
        categories.add(new Pair<>("KEYBOARD_SWITCH", "Keyboard Switch"));
        categories.add(new Pair<>("KEYCAPS", "Key caps"));
        categories.add(new Pair<>("DIY_KIT", "Keyboard DIY Kit"));

        // COFFEE
        categories.add(new Pair<>("COFFEE_GRINDER", "Coffee Grinder"));
        categories.add(new Pair<>("COFFEE_BREWER", "Coffee Brewer"));

        categories.forEach(category -> {
            ItemCategoryDO categoryDO = new ItemCategoryDO();
            categoryDO.setCategoryId(category.getFirst());
            categoryDO.setCategoryName(category.getSecond());

            try {
                itemCategoryRepository.save(categoryDO);
            } catch (Exception e) {
                throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
            }
        });
    }
}
