package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.KnowledgeDO;
import id.thesis.shumishumi.foundation.model.result.primarykey.KnowledgeDOPK;
import id.thesis.shumishumi.foundation.repository.KnowledgeRepository;
import id.thesis.shumishumi.foundation.service.KnowledgeDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeDAOImpl implements KnowledgeDAO {

    private final static Logger LOGGER = LoggerFactory.getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @Override
    public KnowledgeDO queryByTypeAndKey(String type, String key) {
        LogUtil.info(LOGGER, String.format("knowledgeDAO#queryByTypeAndKey[type=%s,key=%s]", type, key));
        KnowledgeDO knowledgeDO;
        try {
            knowledgeDO = knowledgeRepository.findById(new KnowledgeDOPK(key, type)).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("knowledgeDAO#queryByTypeAndKey[knowledge=%s]", knowledgeDO));
        return knowledgeDO;
    }

    @Override
    public void create(String type, String key, String knowledge) {
        LogUtil.info(LOGGER, String.format("knowledgeDAO#create[type=%s,key=%s,knowledge=%s]", type, key, knowledge));
        try {
            KnowledgeDO knowledgeDO = new KnowledgeDO();
            knowledgeDO.setPk(new KnowledgeDOPK(key, type));
            knowledgeDO.setKnowledge(knowledge);

            knowledgeRepository.save(knowledgeDO);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void update(String type, String key, String knowledge) {
        LogUtil.info(LOGGER, String.format("knowledgeDAO#update[type=%s,key=%s,knowledge=%s]", type, key, knowledge));
        try {
            knowledgeRepository.updateKnowledgeValue(type, key, knowledge);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }
}
