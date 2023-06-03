package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.service.KnowledgeService;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.foundation.service.KnowledgeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnowledgeDAO knowledgeDAO;

    @Override
    public void addToKnowledge(ItemVO itemVO) {

    }

    @Override
    public List<String> queryKnowledge(String key, String type) {
        return null;
    }
}
