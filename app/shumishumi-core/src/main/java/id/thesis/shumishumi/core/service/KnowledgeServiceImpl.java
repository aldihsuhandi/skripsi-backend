package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.service.KnowledgeService;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.enumeration.KnowledgeKeyEnum;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.foundation.model.result.KnowledgeDO;
import id.thesis.shumishumi.foundation.service.KnowledgeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnowledgeDAO knowledgeDAO;

    @Override
    public void addItemToKnowledge(ItemVO itemVO) {
        addToKnowledge(KnowledgeKeyEnum.CATEGORY.getKey(),
                itemVO.getItemCategory().getCategoryName(), itemVO.getItemId());
        addToKnowledge(KnowledgeKeyEnum.HOBBY.getKey(),
                itemVO.getHobby().getHobbyName(), itemVO.getItemId());
        addToKnowledge(KnowledgeKeyEnum.MERCHANT_LEVEL.getKey(),
                itemVO.getMerchantLevel().getInterestLevelName(), itemVO.getItemId());
        if (itemVO.getUserLevel() != null) {
            addToKnowledge(KnowledgeKeyEnum.USER_LEVEL.getKey(),
                    itemVO.getUserLevel().getInterestLevelName(), itemVO.getItemId());
        }
    }

    @Override
    public void removeItemFromKnowledge(ItemVO itemVO) {
        removeFromKnowledge(KnowledgeKeyEnum.CATEGORY.getKey(),
                itemVO.getItemCategory().getCategoryName(), itemVO.getItemId());
        removeFromKnowledge(KnowledgeKeyEnum.HOBBY.getKey(),
                itemVO.getHobby().getHobbyName(), itemVO.getItemId());
        removeFromKnowledge(KnowledgeKeyEnum.MERCHANT_LEVEL.getKey(),
                itemVO.getMerchantLevel().getInterestLevelName(), itemVO.getItemId());
        if (itemVO.getUserLevel() != null) {
            removeFromKnowledge(KnowledgeKeyEnum.USER_LEVEL.getKey(),
                    itemVO.getUserLevel().getInterestLevelName(), itemVO.getItemId());
        }
    }

    @Override
    public List<String> queryKnowledge(String key, String type) {
        List<String> knowledges = new ArrayList<>();
        KnowledgeDO knowledgeDO = knowledgeDAO.queryByTypeAndKey(type, key);
        if (knowledgeDO == null) {
            return knowledges;
        }

        knowledges = new ArrayList<>(Arrays.asList(knowledgeDO.
                getKnowledge().split(CommonConst.SEPARATOR_SPLIT)));
        return knowledges;
    }


    private void addToKnowledge(String key, String type, String itemId) {
        List<String> knowledges = queryKnowledge(key, type);
        boolean isNew = knowledges.isEmpty();

        knowledges.add(itemId);

        StringBuilder knowledgeSb = new StringBuilder();
        knowledges.stream().distinct().forEach(knowledge -> knowledgeSb.
                append(knowledge).append(CommonConst.SEPARATOR));

        String knowledgeStr = knowledgeSb.substring(0, knowledgeSb.length() - 1);

        if (isNew) {
            knowledgeDAO.create(type, key, knowledgeStr);
        } else {
            knowledgeDAO.update(type, key, knowledgeStr);
        }
    }

    private void removeFromKnowledge(String key, String type, String itemId) {
        List<String> knowledges = queryKnowledge(key, type);
        if (knowledges.isEmpty() || !knowledges.contains(itemId)) {
            return;
        }

        knowledges.remove(itemId);
        StringBuilder knowledgeSb = new StringBuilder();
        knowledges.stream().distinct().forEach(knowledge -> knowledgeSb.
                append(knowledge).append(CommonConst.SEPARATOR));

        String knowledgeStr = knowledgeSb.substring(0, knowledgeSb.length() - 1);
        knowledgeDAO.update(type, key, knowledgeStr);
    }
}
