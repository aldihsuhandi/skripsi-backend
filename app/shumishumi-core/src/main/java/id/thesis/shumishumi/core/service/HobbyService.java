/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.viewobject.HobbyVO;

import java.util.List;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: HobbyService.java, v 0.1 2023‐01‐18 10:51 Aldih Suhandi Exp $$
 */
public interface HobbyService {
    List<HobbyVO> queryAll();

    HobbyVO query(String key, String identifier);

    void create(String hobbyName);
}
