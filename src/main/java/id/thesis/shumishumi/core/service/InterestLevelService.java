/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.viewobject.InterestLevelVO;

import java.util.List;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: InterestLevelService.java, v 0.1 2023‐01‐18 10:51 Aldih Suhandi Exp $$
 */
public interface InterestLevelService {
    List<InterestLevelVO> queryAll();

    InterestLevelVO query(String key, String identifier);
}
