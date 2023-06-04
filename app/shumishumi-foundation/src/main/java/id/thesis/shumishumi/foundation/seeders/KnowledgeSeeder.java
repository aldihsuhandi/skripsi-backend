package id.thesis.shumishumi.foundation.seeders;

import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.KnowledgeService;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.foundation.repository.ItemRepository;
import id.thesis.shumishumi.foundation.repository.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KnowledgeSeeder extends BaseSeeder {

    @Autowired
    private KnowledgeService knowledgeService;

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    void setOrder() {
        this.order = 11;
    }

    @Override
    void setSeederName() {
        this.seederName = "knowledge seeder";
    }

    @Override
    void deleteRecords() {
        knowledgeRepository.deleteAll();
    }

    @Override
    void seed() {
        itemRepository.findAll().forEach(itemDO -> {
            String itemId = itemDO.getItemId();
            ItemVO itemVO = itemService.queryById(itemId, false);

            knowledgeService.addItemToKnowledge(itemVO);
        });
    }
}
