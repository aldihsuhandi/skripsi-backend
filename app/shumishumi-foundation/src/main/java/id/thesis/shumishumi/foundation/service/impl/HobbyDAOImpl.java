/**
 *
 */
package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.HobbyDO;
import id.thesis.shumishumi.foundation.repository.HobbyRepository;
import id.thesis.shumishumi.foundation.service.HobbyDAO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: HobbyDAOImpl.java, v 0.1 2023‐01‐17 13:51 Aldih Suhandi Exp $$
 */
@Service
public class HobbyDAOImpl implements HobbyDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private HobbyRepository hobbyRepository;

    @Override
    public List<HobbyDO> queryAll() {
        LogUtil.info(DALGEN_LOGGER, "hobbyDAO#queryAll[]");

        List<HobbyDO> result;
        try {
            result = hobbyRepository.findAll();
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("hobbyDAO#queryAll[result=%s]", result));
        return result;
    }

    @Override
    public HobbyDO queryById(String hobbyId) {
        LogUtil.info(DALGEN_LOGGER, String.format("hobbyDAO#queryById[hobbyId=%s]", hobbyId));

        hobbyId = StringUtils.defaultIfEmpty(hobbyId, "");

        HobbyDO hobby = null;
        try {
            hobby = hobbyRepository.findById(hobbyId).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("hobbyDAO#queryById[result=%s]", hobby));
        return hobby;
    }

    @Override
    public HobbyDO queryByName(String hobbyName) {
        LogUtil.info(DALGEN_LOGGER, String.format("hobbyDAO#queryByName[hobbyName=%s]", hobbyName));

        hobbyName = StringUtils.defaultIfEmpty(hobbyName, "");

        HobbyDO hobby = null;
        try {
            hobby = hobbyRepository.findByHobbyName(hobbyName).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("hobbyDAO#queryByName[result=%s]", hobby));
        return hobby;
    }

    @Override
    public void create(String hobbyId, String hobbyName) {
        LogUtil.info(DALGEN_LOGGER, String.format("hobbyDAO#create[hobbyId=%s,hobbyName=%s]", hobbyId, hobbyName));
        HobbyDO hobbyDO = new HobbyDO();
        hobbyDO.setHobbyId(hobbyId);
        hobbyDO.setHobbyName(hobbyName);

        try {
            hobbyRepository.save(hobbyDO);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }
}
