package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.request.ItemCrowdDAORequest;
import id.thesis.shumishumi.foundation.model.request.UserCrowdDAORequest;
import id.thesis.shumishumi.foundation.model.result.ItemCrowdDO;
import id.thesis.shumishumi.foundation.model.result.UserCrowdDO;

import java.util.List;

public interface CrowdDAO {
    List<ItemCrowdDO> queryItemCrowd(ItemCrowdDAORequest daoRequest);

    List<UserCrowdDO> queryUserCrowd(UserCrowdDAORequest daoRequest);
}
