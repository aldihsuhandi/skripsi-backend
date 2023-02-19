/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.viewobject.ItemCategoryVO;

import java.util.List;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ItemCategoryService.java, v 0.1 2023‐01‐18 10:52 Aldih Suhandi Exp $$
 */
public interface ItemCategoryService {
    List<ItemCategoryVO> queryAll();

    ItemCategoryVO query(String key, String identifier);

    void create(String categoryName);
}
