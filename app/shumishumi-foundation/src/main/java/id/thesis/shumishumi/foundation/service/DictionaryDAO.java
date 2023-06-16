package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.result.DictionaryDO;

import java.util.List;

public interface DictionaryDAO {
    List<DictionaryDO> queryByType(String dictionaryType);

    List<DictionaryDO> queryAll();

    DictionaryDO queryByKey(String key);
}
