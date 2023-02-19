/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.service.service;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.viewobject.ActivityVO;
import id.thesis.shumishumi.common.model.viewobject.UserActivityVO;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.converter.ViewObjectConverter;
import id.thesis.shumishumi.core.service.ActivityService;
import id.thesis.shumishumi.foundation.model.request.UserActivityDAORequest;
import id.thesis.shumishumi.foundation.service.ActivityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ActivityServiceImpl.java, v 0.1 2023‐01‐19 9:57 AM Aldih Suhandi Exp $$
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDAO activityDAO;

    @Override
    public void createUserActivity(String userId, String itemId, String activityId) {
        UserActivityDAORequest daoRequest = new UserActivityDAORequest();
        daoRequest.setUserActivityId(FunctionUtil.generateUUID());
        daoRequest.setUserId(userId);
        daoRequest.setItemId(itemId);
        daoRequest.setActivitytId(activityId);

        activityDAO.insertUserActivity(daoRequest);
    }

    @Override
    public List<UserActivityVO> queryUserActivity(String userID) {
        return activityDAO.queryUserActivity(userID).stream()
                .map(userActivityDO -> {
                    UserActivityVO userActivityVO = ViewObjectConverter.toViewObject(userActivityDO);
                    userActivityVO.setActivityInfo(
                            this.queryActivity(userActivityDO.getActivityId(), DatabaseConst.ACTIVITY_ID));

                    return userActivityVO;
                }).collect(Collectors.toList());
    }

    @Override
    public ActivityVO queryActivity(String key, String identifier) {
        if (DatabaseConst.ACTIVITY_ID.equals(identifier)) {
            return ViewObjectConverter.toViewObject(activityDAO.queryActivityById(key));
        }
        if (DatabaseConst.ACTIVITY_NAME.equals(identifier)) {
            return ViewObjectConverter.toViewObject(activityDAO.queryActivityByName(key));
        }

        throw new ShumishumiException(ShumishumiErrorCodeEnum.SYSTEM_ERROR.getErrorMsg(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }
}
