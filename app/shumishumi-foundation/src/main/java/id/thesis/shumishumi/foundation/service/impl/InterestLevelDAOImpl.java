package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.InterestLevelDO;
import id.thesis.shumishumi.foundation.repository.InterestLevelRepository;
import id.thesis.shumishumi.foundation.service.InterestLevelDAO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestLevelDAOImpl implements InterestLevelDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private InterestLevelRepository interestLevelRepository;

    @Override
    public List<InterestLevelDO> queryAll() {
        LogUtil.info(DALGEN_LOGGER, "interestLevelDAO#queryAll[]");

        List<InterestLevelDO> result;
        try {
            result = interestLevelRepository.findAll();
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, "interestLevelDAO#queryAll[result=%s]", result);
        return result;
    }

    @Override
    public InterestLevelDO queryById(String interestLevelId) {
        LogUtil.info(DALGEN_LOGGER, String.format("interestLevelDAO#queryById[interestLevelId=%s]", interestLevelId));

        interestLevelId = StringUtils.defaultIfEmpty(interestLevelId, "");

        InterestLevelDO result = null;
        try {
            result = interestLevelRepository.findById(interestLevelId).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("interestLevelDAO#queryById[result=%s]", result));
        return result;
    }

    @Override
    public InterestLevelDO queryByName(String interestLevelName) {
        LogUtil.info(DALGEN_LOGGER, String.format("interestLevelDAO#queryByName[interestLevelId=%s]", interestLevelName));

        interestLevelName = StringUtils.defaultIfEmpty(interestLevelName, "");

        InterestLevelDO result = null;
        try {
            result = interestLevelRepository.findByInterestLevelName(interestLevelName).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("interestLevelDAO#queryByName[result=%s]", result));
        return result;
    }
}
