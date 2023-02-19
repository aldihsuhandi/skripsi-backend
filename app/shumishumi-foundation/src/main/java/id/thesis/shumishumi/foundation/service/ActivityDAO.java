/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.request.UserActivityDAORequest;
import id.thesis.shumishumi.foundation.model.result.ActivityDO;
import id.thesis.shumishumi.foundation.model.result.UserActivityDO;

import java.util.List;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ActivityDAO.java, v 0.1 2023‐01‐19 9:16 AM Aldih Suhandi Exp $$
 */
public interface ActivityDAO {
    void insertUserActivity(UserActivityDAORequest request);

    List<UserActivityDO> queryUserActivity(String userId);

    ActivityDO queryActivityById(String activityId);

    ActivityDO queryActivityByName(String activityName);
}
