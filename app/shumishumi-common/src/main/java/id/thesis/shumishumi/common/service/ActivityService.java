package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.facade.model.viewobject.ItemVO;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    void addActivity(String userId, ItemVO itemVO, int value);

    List<Map<String, String>> queryActivity(String userId);
}
