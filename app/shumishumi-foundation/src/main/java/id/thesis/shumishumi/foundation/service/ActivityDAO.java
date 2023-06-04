package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.result.ActivityDO;

import java.util.List;

public interface ActivityDAO {
    List<ActivityDO> queryByUserId(String userId);

    void create(ActivityDO activity);

    void update(ActivityDO activity);
}
