package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.request.ItemCrowdDAORequest;
import id.thesis.shumishumi.foundation.model.request.UserCrowdDAORequest;
import id.thesis.shumishumi.foundation.model.result.ItemCrowdDO;
import id.thesis.shumishumi.foundation.model.result.UserCrowdDO;
import id.thesis.shumishumi.foundation.repository.ItemCrowdRepository;
import id.thesis.shumishumi.foundation.repository.UserCrowdRepository;
import id.thesis.shumishumi.foundation.service.CrowdDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrowdDAOImpl implements CrowdDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private UserCrowdRepository userCrowdRepository;

    @Autowired
    private ItemCrowdRepository itemCrowdRepository;

    @Override
    public List<ItemCrowdDO> queryItemCrowd(ItemCrowdDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("crowdDAO#queryItemCrowd[request=%s]", daoRequest));

        List<ItemCrowdDO> result;
        try {
            result = itemCrowdRepository.queryByCrowdId(daoRequest.getCrowdId());
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("crowdDAO#queryUserCrowd[result=%s]", result));

        return result;
    }

    @Override
    public List<UserCrowdDO> queryUserCrowd(UserCrowdDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("crowdDAO#queryUserCrowd[request=%s]", daoRequest));

        List<UserCrowdDO> result;
        try {
            result = userCrowdRepository.queryByUserId(daoRequest.getUserId());
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("crowdDAO#queryUserCrowd[result=%s]", result));

        return result;
    }
}
