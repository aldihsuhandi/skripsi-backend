package id.thesis.shumishumi.core.fetch;

import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ItemFetchService {
    private final Map<String, ItemVO> itemCache = new HashMap<>();

    public void putToCache(ItemVO itemVO) {
        if (itemVO == null) {
            return;
        }

        itemCache.put(itemVO.getItemId(), itemVO);
    }

    public ItemVO fetchFromCache(String itemId) {
        return itemCache.get(itemId);
    }
}
