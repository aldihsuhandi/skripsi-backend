package id.thesis.shumishumi.foundation.dalgen.service;

import id.thesis.shumishumi.foundation.dalgen.model.result.InterestLevelDO;

import java.util.List;

public interface InterestLevelDAO {
    List<InterestLevelDO> queryAll();

    InterestLevelDO queryById(String interestLevelId);

    InterestLevelDO queryByName(String interestLevelName);
}
