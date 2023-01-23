package id.thesis.shumishumi.core.fetch;

import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.common.util.comparator.ItemVOGmtCreateComparator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<ItemVO> fetchAll() {
        return new ArrayList<>(itemCache.values()).stream().
                sorted(new ItemVOGmtCreateComparator()).collect(Collectors.toList());
    }

    public void clearCache() {
        itemCache.clear();
    }
}
