package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.result.InterestLevelDO;

import java.util.List;

public interface InterestLevelDAO {
    List<InterestLevelDO> queryAll();

    InterestLevelDO queryById(String interestLevelId);

    InterestLevelDO queryByName(String interestLevelName);
}
