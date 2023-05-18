package id.thesis.shumishumi.core.processor.item;

import id.thesis.shumishumi.common.service.CrowdService;
import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.ItemWishlistService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.core.converter.SummaryConverter;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.summary.ItemSummary;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.item.RecommendRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.item.RecommendResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecommendProcessor implements BaseProcessor {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemWishlistService itemWishlistService;

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

        result.setItems(itemVOS.stream().
                map(itemVO -> {
                    int totalWishlist = itemWishlistService.countItemWishlist(itemVO.getItemId());
                    ItemSummary itemSummary = SummaryConverter.toSummary(itemVO, totalWishlist);
                    itemSummary.setInWishlist(itemWishlistService.checkItemInWishlist(
                            sessionVO.getUserId(), itemSummary.getItemId()));

                    return itemSummary;
                }).collect(Collectors.toList()));
    }
}
