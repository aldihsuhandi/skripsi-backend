package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.facade.model.viewobject.ItemVO;

import java.util.List;

public interface KnowledgeService {
    void addItemToKnowledge(ItemVO itemVO);

    void removeItemFromKnowledge(ItemVO itemVO);

    List<String> queryKnowledge(String key, String type);
}
