package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.service.DictionaryService;
import id.thesis.shumishumi.core.fetch.DictionaryFetchService;
import id.thesis.shumishumi.foundation.model.result.DictionaryDO;
import id.thesis.shumishumi.foundation.service.DictionaryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryDAO dictionaryDAO;

    @Autowired
    private DictionaryFetchService dictionaryFetchService;

    @Override
    public void refreshCache() {
        dictionaryFetchService.clearCache();
        List<DictionaryDO> dictionaries = dictionaryDAO.queryAll();
        dictionaries.forEach(dictionary -> {
            String dictionaryType = dictionary.getDictionaryType();
            String displayName = dictionary.getDisplayName();

            dictionaryFetchService.putToCache(dictionaryType, displayName);
        });
    }

    @Override
    public List<String> queryByType(String dictionaryType) {
        List<String> result = dictionaryFetchService.fetchFromCache(dictionaryType);
        if (result.isEmpty()) {
            result = dictionaryDAO.queryByType(dictionaryType).stream()
                    .map(DictionaryDO::getDisplayName).collect(Collectors.toList());
        }

        return result;
    }

    @Override
    public String queryByKey(String key) {
        DictionaryDO dictionaryDO = dictionaryDAO.queryByKey(key);
        if (dictionaryDO == null) {
            return "";
        }

        return dictionaryDO.getDisplayName();
    }
}
