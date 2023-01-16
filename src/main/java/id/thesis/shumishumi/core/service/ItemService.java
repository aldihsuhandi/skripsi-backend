/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.request.item.CreateItemInnerRequest;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ItemSerivce.java, v 0.1 2023‐01‐16 5:01 PM Aldih Suhandi Exp $$
 */
public interface ItemService {
    void create(CreateItemInnerRequest request);
}
