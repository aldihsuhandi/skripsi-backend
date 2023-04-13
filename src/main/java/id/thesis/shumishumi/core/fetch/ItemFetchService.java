package id.thesis.shumishumi.core.fetch;

import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.common.util.comparator.ItemVOGmtCreateComparator;
import id.thesis.shumishumi.common.util.constant.LogConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemFetchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogConstant.SERVICE_LOGGER);

    private final Map<String, ItemVO> itemCache = new HashMap<>();

    public void putToCache(ItemVO itemVO) {
        LogUtil.info(LOGGER, String.format("itemFetchService#putToCache[itemVO=%s]", itemVO));
        if (itemVO == null) {
            return;
        }

        itemCache.put(itemVO.getItemId(), itemVO);
    }

    public ItemVO fetchFromCache(String itemId) {
        LogUtil.info(LOGGER, String.format("itemFetchService#fetchFromCache[itemId=%s]", itemId));
        ItemVO item = itemCache.get(itemId);
        LogUtil.info(LOGGER, String.format("itemFetchService#fetchFromCache[result=%s]", item));

        return item;
    }

    public List<ItemVO> fetchAll() {
        List<ItemVO> items = new ArrayList<>(itemCache.values()).stream().
                sorted(new ItemVOGmtCreateComparator()).collect(Collectors.toList());
        LogUtil.info(LOGGER, String.format("itemFetchService#fetchAll[result=%s]", items));

        return items;
    }

    public void clearCache() {
        itemCache.clear();
    }
}
