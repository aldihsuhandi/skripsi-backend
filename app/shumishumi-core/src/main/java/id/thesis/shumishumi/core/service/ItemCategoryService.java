/**
 *
 */
package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.facade.model.viewobject.ItemCategoryVO;

import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ItemCategoryService.java, v 0.1 2023‐01‐18 10:52 Aldih Suhandi Exp $$
 */
public interface ItemCategoryService {
    List<ItemCategoryVO> queryAll();

    ItemCategoryVO query(String key, String identifier);

    void create(String categoryName);
}
