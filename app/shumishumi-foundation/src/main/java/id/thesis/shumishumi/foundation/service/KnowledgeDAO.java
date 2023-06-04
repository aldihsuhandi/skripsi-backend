package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.result.KnowledgeDO;

public interface KnowledgeDAO {
    KnowledgeDO queryByTypeAndKey(String type, String key);

    void create(String type, String key, String knowledge);

    void update(String type, String key, String knowledge);
}
