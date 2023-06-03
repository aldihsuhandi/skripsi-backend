package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.ActivityDO;
import id.thesis.shumishumi.foundation.repository.ActivityRepository;
import id.thesis.shumishumi.foundation.service.ActivityDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityDAOImpl implements ActivityDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<ActivityDO> queryByUserId(String userId) {
        LogUtil.info(LOGGER, String.format("activityDAO#queryByUserId[userId=%s]", userId));

        List<ActivityDO> activities;
        try {
            activities = activityRepository.findByUserId(userId);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("activityDAO#queryByUserId[activities=%s]", activities));
        return activities;
    }

    @Override
    public void create(ActivityDO activity) {
        LogUtil.info(LOGGER, String.format("activityDAO#create[activity=%s]", activity));
        try {
            activityRepository.save(activity);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void update(ActivityDO activity) {
        LogUtil.info(LOGGER, String.format("activityDAO#update[activity=%s]", activity));
        try {
            activityRepository.save(activity);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }
}
