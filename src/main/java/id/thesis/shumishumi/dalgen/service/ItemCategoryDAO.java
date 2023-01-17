/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.dalgen.service;

import id.thesis.shumishumi.dalgen.model.result.ItemCategoryDO;

import java.util.List;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: CategoryDAO.java, v 0.1 2023‐01‐17 13:08 Aldih Suhandi Exp $$
 */

public interface ItemCategoryDAO {
    void create(String categoryId, String categoryName);
    ItemCategoryDO queryById(String categoryId);
    ItemCategoryDO queryByName(String categoryName);
    List<ItemCategoryDO> queryAll();
}
