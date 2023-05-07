package id.thesis.shumishumi.common.service;

import java.util.List;

public interface DictionaryService {
    void refreshCache();

    List<String> queryByType(String dictionaryType);
}
