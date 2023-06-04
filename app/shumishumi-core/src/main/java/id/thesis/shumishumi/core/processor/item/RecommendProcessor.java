package id.thesis.shumishumi.core.processor.item;

import id.thesis.shumishumi.common.service.ActivityService;
import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.ItemWishlistService;
import id.thesis.shumishumi.common.service.KnowledgeService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.core.converter.SummaryConverter;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.enumeration.KnowledgeKeyEnum;
import id.thesis.shumishumi.facade.model.summary.ItemSummary;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.item.RecommendRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.item.RecommendResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RecommendProcessor implements BaseProcessor {

    @Autowired
    private ItemService itemService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private KnowledgeService knowledgeService;

    @Autowired
    private ItemWishlistService itemWishlistService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        RecommendRequest request = (RecommendRequest) baseRequest;
        RecommendResult result = (RecommendResult) baseResult;

        String userId = sessionService.query(request.getSessionId()).getUserId();

        List<Map<String, String>> activities = activityService.queryActivity(userId);
        List<String> itemIds = new ArrayList<>();
        activities.forEach(activity -> itemIds.addAll(getItemIdFromActivity(activity)));
        activities.forEach(activity -> {
            Map<String, String> a = new HashMap<>();
            a.put(CommonConst.ACTIVITY_ITEM_HOBBY, activity.get(CommonConst.ACTIVITY_ITEM_HOBBY));
            a.put(CommonConst.ACTIVITY_ITEM_CATEGORY, activity.get(CommonConst.ACTIVITY_ITEM_CATEGORY));

            itemIds.addAll(getItemIdFromActivity(a));
        });

        List<ItemSummary> items = itemIds.stream().distinct().map(id -> {
            ItemVO itemVO = itemService.queryById(id, true);
            return SummaryConverter.toSummary(itemVO);
        }).collect(Collectors.toList());

        result.setItems(items.subList(0, Math.min(10, items.size())));
    }

    private List<String> getItemIdFromActivity(Map<String, String> activity) {
        Set<String> idSet = new HashSet<>(knowledgeService.
                queryKnowledge(KnowledgeKeyEnum.HOBBY.getKey(), activity.get(CommonConst.ACTIVITY_ITEM_HOBBY)));
        idSet.retainAll(new HashSet<>(knowledgeService.queryKnowledge(KnowledgeKeyEnum.CATEGORY.getKey(),
                activity.get(CommonConst.ACTIVITY_ITEM_CATEGORY))));
        if (activity.containsKey(CommonConst.ACTIVITY_USER_LEVEL) && StringUtils.
                isNotEmpty(activity.get(CommonConst.ACTIVITY_USER_LEVEL))) {
            idSet.retainAll(new HashSet<>(knowledgeService.queryKnowledge(KnowledgeKeyEnum.USER_LEVEL.getKey(),
                    activity.get(CommonConst.ACTIVITY_USER_LEVEL))));
        }
        if (activity.containsKey(CommonConst.ACTIVITY_MERCHANT_LEVEL) && StringUtils.
                isNotEmpty(activity.get(CommonConst.ACTIVITY_MERCHANT_LEVEL))) {
            idSet.retainAll(new HashSet<>(knowledgeService.queryKnowledge(KnowledgeKeyEnum.MERCHANT_LEVEL.getKey(),
                    activity.get(CommonConst.ACTIVITY_MERCHANT_LEVEL))));
        }

        return new ArrayList<>(idSet);
    }
}
