/**
 *
 */
package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.common.util.database.StatementBuilder;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.mapper.ItemCategoryDOMapper;
import id.thesis.shumishumi.foundation.model.result.ItemCategoryDO;
import id.thesis.shumishumi.foundation.repository.ItemCategoryRepository;
import id.thesis.shumishumi.foundation.service.ItemCategoryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ItemCategoryDAOImpl.java, v 0.1 2023‐01‐17 13:14 Aldih Suhandi Exp $$
 */
@Service
public class ItemCategoryDAOImpl implements ItemCategoryDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    private static final Logger DAO_LOGGER = LoggerFactory.
            getLogger(LogConstant.DAO_LOGGER);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    public void create(String categoryId, String categoryName) {
        LogUtil.info(DALGEN_LOGGER, String.format("itemCategoryDAO#create[categoryId=%s,categoryName=%s]", categoryId, categoryName));
        ItemCategoryDO categoryDO = new ItemCategoryDO();
        categoryDO.setCategoryId(categoryId);
        categoryDO.setCategoryName(categoryName);

        try {
            itemCategoryRepository.save(categoryDO);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ItemCategoryDO queryById(String categoryId) {
        LogUtil.info(DALGEN_LOGGER, String.format("itemCategoryDAO#queryById[categoryId=%s]", categoryId));

        ItemCategoryDO result;
        try {
            result = itemCategoryRepository.findById(categoryId).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("itemCategoryDAO#queryById[result=%s]", result));

        return result;
    }

    @Override
    public ItemCategoryDO queryByName(String categoryName) {
        LogUtil.info(DALGEN_LOGGER, String.format("itemCategoryDAO#queryByName[categoryName=%s]", categoryName));

        ItemCategoryDO result;
        try {
            result = itemCategoryRepository.findByCategoryName(categoryName).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("itemCategoryDAO#queryByName[result=%s]", result));

        return result;
    }

    @Override
    public List<ItemCategoryDO> queryAll() {
        LogUtil.info(DALGEN_LOGGER, "itemCategoryDAO#queryAll[]");

        List<ItemCategoryDO> result;
        try {
            result = itemCategoryRepository.findAll();
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("itemCategoryDAO#queryAll[result=%s]", result));

        return result;
    }
}
