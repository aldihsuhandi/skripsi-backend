package id.thesis.shumishumi.dalgen.service;

import id.thesis.shumishumi.dalgen.model.result.InterestLevelDO;

import java.util.List;

public interface InterestLevelDAO {
    List<InterestLevelDO> queryAll();

    InterestLevelDO queryById(String interestLevelId);

    InterestLevelDO queryByName(String interestLevelName);
}
