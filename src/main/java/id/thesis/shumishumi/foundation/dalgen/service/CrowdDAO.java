package id.thesis.shumishumi.foundation.dalgen.service;

import id.thesis.shumishumi.foundation.dalgen.model.request.ItemCrowdDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.request.UserCrowdDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.result.ItemCrowdDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.UserCrowdDO;

import java.util.List;

public interface CrowdDAO {
    List<ItemCrowdDO> queryItemCrowd(ItemCrowdDAORequest daoRequest);

    List<UserCrowdDO> queryUserCrowd(UserCrowdDAORequest daoRequest);
}
