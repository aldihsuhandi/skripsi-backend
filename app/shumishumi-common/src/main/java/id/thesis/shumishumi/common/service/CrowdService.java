package id.thesis.shumishumi.common.service;

import java.util.List;

public interface CrowdService {
    List<String> queryItemIdsFromCrowd(String crowdId);

    List<String> queryCrowdId(String userId);
}
