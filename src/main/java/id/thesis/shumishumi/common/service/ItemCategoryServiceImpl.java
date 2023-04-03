/**
 * 
 *
 */
package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.converter.ViewObjectConverter;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.viewobject.ItemCategoryVO;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.service.ItemCategoryService;
import id.thesis.shumishumi.foundation.dalgen.service.ItemCategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ItemCategoryServiceImpl.java, v 0.1 2023‐01‐18 10:56 Aldih Suhandi Exp $$
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCategoryDAO itemCategoryDAO;

    @Override
    public List<ItemCategoryVO> queryAll() {
        return itemCategoryDAO.queryAll().stream().map(
                ViewObjectConverter::toViewObject).collect(Collectors.toList());
    }

    @Override
    public ItemCategoryVO query(String key, String identifier) {
        if (DatabaseConst.CATEGORY_ID.equalsIgnoreCase(identifier)) {
            return ViewObjectConverter.toViewObject(
                    itemCategoryDAO.queryById(key));
        } else if (DatabaseConst.CATEGORY_NAME.equalsIgnoreCase(identifier)) {
            return ViewObjectConverter.toViewObject(
                    itemCategoryDAO.queryByName(key));
        }
        throw new ShumishumiException("wrong identifier", ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void create(String categoryName) {
        itemCategoryDAO.create(FunctionUtil.generateUUID(), categoryName);
    }
}
