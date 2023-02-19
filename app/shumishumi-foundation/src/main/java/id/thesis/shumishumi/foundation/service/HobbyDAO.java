/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.result.HobbyDO;

import java.util.List;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: HobbyDAO.java, v 0.1 2023‐01‐17 13:50 Aldih Suhandi Exp $$
 */
public interface HobbyDAO {
    List<HobbyDO> queryAll();
    HobbyDO queryById(String hobbyId);
    HobbyDO queryByName(String hobbyName);
    void create(String hobbyId, String hobbyName);
}
