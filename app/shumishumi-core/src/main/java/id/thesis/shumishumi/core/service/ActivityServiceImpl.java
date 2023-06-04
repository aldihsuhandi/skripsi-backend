package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.service.ActivityService;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.common.util.JSONStringUtil;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.foundation.model.result.ActivityDO;
import id.thesis.shumishumi.foundation.service.ActivityDAO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDAO activityDAO;

    @Override
    public void addActivity(String userId, ItemVO itemVO, int value) {
        if (StringUtils.isEmpty(userId)) {
            return;
        }

        Map<String, String> activityMap = new HashMap<>();
        activityMap.put(CommonConst.ACTIVITY_ITEM_CATEGORY, itemVO.getItemCategory().getCategoryName());
        activityMap.put(CommonConst.ACTIVITY_ITEM_HOBBY, itemVO.getHobby().getHobbyName());
        activityMap.put(CommonConst.ACTIVITY_MERCHANT_LEVEL, itemVO.getMerchantLevel().getInterestLevelName());
        activityMap.put(CommonConst.ACTIVITY_USER_LEVEL, itemVO.getUserLevel() != null ?
                itemVO.getUserLevel().getInterestLevelName() : "");


        List<ActivityDO> activities = activityDAO.queryByUserId(userId);

        ActivityDO activity = activities.stream().filter(ac -> activityEqual(JSONStringUtil.parseJSONString(ac.getActivity()),
                activityMap)).findFirst().orElse(null);
        if (activity == null) {
            ActivityDO activityDO = new ActivityDO();
            activityDO.setActivityId(FunctionUtil.generateUUID());
            activityDO.setUserId(userId);
            activityDO.setActivity(JSONStringUtil.parseObject(activityMap));
            activityDO.setActivityValue(value);

            activityDAO.create(activityDO);
            return;
        }

        int updatedValue = activity.getActivityValue() + value;
        activity.setActivityValue(updatedValue);

        activityDAO.update(activity);
    }

    @Override
    public List<Map<String, String>> queryActivity(String userId) {
        return activityDAO.queryByUserId(userId).stream()
                .sorted(Comparator.comparingInt(ActivityDO::getActivityValue).reversed())
                .map(activity -> JSONStringUtil.parseJSONString(
                        activity.getActivity())).collect(Collectors.toList());
    }

    private boolean activityEqual(Map<String, String> m1, Map<String, String> m2) {
        if (CollectionUtils.isEmpty(m1) || CollectionUtils.isEmpty(m2)) {
            return false;
        }

        List<String> keys = new ArrayList<>();
        keys.add(CommonConst.ACTIVITY_ITEM_HOBBY);
        keys.add(CommonConst.ACTIVITY_ITEM_CATEGORY);
        keys.add(CommonConst.ACTIVITY_USER_LEVEL);
        keys.add(CommonConst.ACTIVITY_MERCHANT_LEVEL);

        boolean res = true;
        for (String key : keys) {
            res &= m1.containsKey(key) && m2.containsKey(key) &&
                    StringUtils.equals(m1.get(key), m2.get(key));
        }

        return res;
    }
}
