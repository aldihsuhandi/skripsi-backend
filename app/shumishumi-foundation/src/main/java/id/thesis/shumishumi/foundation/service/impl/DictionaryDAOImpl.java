package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.DictionaryDO;
import id.thesis.shumishumi.foundation.repository.DictionaryRepository;
import id.thesis.shumishumi.foundation.service.DictionaryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryDAOImpl implements DictionaryDAO {

    private final static Logger LOGGER = LoggerFactory.getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private DictionaryRepository dictionaryRepository;

    @Override
    public List<DictionaryDO> queryByType(String dictionaryType) {
        LogUtil.info(LOGGER, String.format("dictionaryDAO#queryByType[dictionaryType=%s]", dictionaryType));

        List<DictionaryDO> result;
        try {
            result = dictionaryRepository.findByDictionaryType(dictionaryType);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("dictionaryDAO#queryByType[result=%s]", result));
        return result;
    }

    @Override
    public List<DictionaryDO> queryAll() {
        LogUtil.info(LOGGER, "dictionaryDAO#queryAll");

        List<DictionaryDO> result;
        try {
            result = dictionaryRepository.findAll();
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("dictionaryDAO#queryAll[result=%s]", result));
        return result;
    }

    @Override
    public DictionaryDO queryByKey(String key) {
        LogUtil.info(LOGGER, "dictionaryDAO#queryByKey[key=%s]", key);
        DictionaryDO dictionaryDO;
        try {
            dictionaryDO = dictionaryRepository.findByDictionaryName(key).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, "dictionaryDAO#queryByKey[result=%s]\n", dictionaryDO);
        return dictionaryDO;
    }
}
