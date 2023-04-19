package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.service.CrowdService;
import id.thesis.shumishumi.foundation.model.request.ItemCrowdDAORequest;
import id.thesis.shumishumi.foundation.model.request.UserCrowdDAORequest;
import id.thesis.shumishumi.foundation.model.result.ItemCrowdDO;
import id.thesis.shumishumi.foundation.model.result.UserCrowdDO;
import id.thesis.shumishumi.foundation.service.CrowdDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrowdServiceImpl implements CrowdService {

    @Autowired
    private CrowdDAO crowdDAO;

    @Override
    public List<String> queryItemIdsFromCrowd(String crowdId) {
        ItemCrowdDAORequest daoRequest = new ItemCrowdDAORequest();
        daoRequest.setCrowdId(crowdId);

        return crowdDAO.queryItemCrowd(daoRequest).stream().
                map(ItemCrowdDO::getItemId).collect(Collectors.toList());
    }

    @Override
    public List<String> queryCrowdId(String userId) {
        UserCrowdDAORequest daoRequest = new UserCrowdDAORequest();
        daoRequest.setUserId(userId);
        return crowdDAO.queryUserCrowd(daoRequest).stream().
                map(UserCrowdDO::getCrowdId).collect(Collectors.toList());
    }
}
