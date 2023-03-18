package id.thesis.shumishumi.process.processor.item;

import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.core.service.CrowdService;
import id.thesis.shumishumi.core.service.ItemService;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.process.processor.BaseProcessor;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.item.RecommendRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import id.thesis.shumishumi.rest.result.item.RecommendResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendProcessor implements BaseProcessor {

    @Autowired
    private ItemService itemService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Autowired
    private CrowdService crowdService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        RecommendResult result = (RecommendResult) baseResult;
        RecommendRequest request = (RecommendRequest) baseRequest;

        if (StringUtils.isBlank(request.getSessionId())) {
            return;
        }

        SessionVO sessionVO = sessionService.query(request.getSessionId());
        if (sessionVO == null || !sessionVO.isActive() ||
                sessionVO.getSessionDt().before(new Date())) {
            return;
        }

        List<ItemVO> itemVOS = new ArrayList<>();

        final Map<String, List<String>> crowdItem = new HashMap<>();
        crowdService.queryCrowdId(sessionVO.getUserId()).forEach(crowdId -> {
            List<String> itemIds = crowdService.queryItemIdsFromCrowd(crowdId);
            crowdItem.put(crowdId, itemIds);
        });

        while (itemVOS.size() <= 10) {
            for (String crowdId : crowdItem.keySet()) {
                List<String> itemIds = crowdItem.get(crowdId);
                ItemVO item = itemService.queryById(itemIds.get(0), true);
                itemVOS.add(item);

                if (itemIds.isEmpty()) {
                    crowdItem.remove(crowdId);
                }

                itemIds.remove(0);
            }

            if (crowdItem.isEmpty()) {
                break;
            }
        }

        result.setItems(itemVOS);
    }
}
