package id.thesis.shumishumi.core.fetch;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class DictionaryFetchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogConstant.SERVICE_LOGGER);

    private final Map<String, Set<String>> dictionaryCache = new HashMap<>();

    public void putToCache(String dictionaryType, String displayName) {
        LogUtil.info(LOGGER, String.format("dictionaryFetchService#putToCache[dictionaryType=%s, displayName=%s]", dictionaryType, displayName));
        if (StringUtils.isEmpty(dictionaryType) || StringUtils.isEmpty(displayName)) {
            return;
        }

        Set<String> dictionaries = new HashSet<>();
        if (dictionaryCache.containsKey(dictionaryType)) {
            dictionaries = dictionaryCache.get(dictionaryType);
        }

        dictionaries.add(displayName);
        dictionaryCache.put(dictionaryType, dictionaries);
    }

    public List<String> fetchFromCache(String dictionaryType) {
        LogUtil.info(LOGGER, String.format("dictionaryFetchService#fetchFromCache[dictionaryType=%s]", dictionaryType));
        if (StringUtils.isEmpty(dictionaryType) || !dictionaryCache.containsKey(dictionaryType)) {
            LogUtil.info(LOGGER, "dictionaryFetchService#fetchFromCache[result=[]]");
            return new ArrayList<>();
        }

        Set<String> dictionaries = dictionaryCache.get(dictionaryType);
        List<String> result = new ArrayList<>(dictionaries);

        LogUtil.info(LOGGER, String.format("dictionaryFetchService#fetchFromCache[result=%s]", result));
        return result;
    }

    public void clearCache() {
        LogUtil.info(LOGGER, "dictionaryFetchService#clearCache");
        dictionaryCache.clear();
    }
}
