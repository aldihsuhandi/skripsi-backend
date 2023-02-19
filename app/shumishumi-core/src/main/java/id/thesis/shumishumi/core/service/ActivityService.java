/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.viewobject.ActivityVO;
import id.thesis.shumishumi.common.model.viewobject.UserActivityVO;

import java.util.List;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ActivityService.java, v 0.1 2023‐01‐19 9:47 AM Aldih Suhandi Exp $$
 */
public interface ActivityService {
    void createUserActivity(String userId, String itemId, String activityId);

    List<UserActivityVO> queryUserActivity(String userID);

    ActivityVO queryActivity(String key, String identifier);


}
