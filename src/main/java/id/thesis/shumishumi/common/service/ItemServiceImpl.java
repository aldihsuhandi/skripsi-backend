/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.converter.ViewObjectConverter;
import id.thesis.shumishumi.common.model.context.PagingContext;
import id.thesis.shumishumi.common.model.request.item.CreateItemInnerRequest;
import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.core.fetch.ItemFetchService;
import id.thesis.shumishumi.core.service.HobbyService;
import id.thesis.shumishumi.core.service.InterestLevelService;
import id.thesis.shumishumi.core.service.ItemCategoryService;
import id.thesis.shumishumi.core.service.ItemService;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.dalgen.converter.ItemDAORequestConverter;
import id.thesis.shumishumi.dalgen.model.request.ItemDAORequest;
import id.thesis.shumishumi.dalgen.service.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ItemServiceImpl.java, v 0.1 2023‐01‐16 5:02 PM Aldih Suhandi Exp $$
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private ItemFetchService itemFetchService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemCategoryService itemCategoryService;

    @Autowired
    private HobbyService hobbyService;

    @Autowired
    private InterestLevelService interestLevelService;

    @Override
    public void create(CreateItemInnerRequest request) {
        ItemDAORequest itemDAORequest = ItemDAORequestConverter.toDAORequest(request);
        itemDAO.create(itemDAORequest);
    }

    @Override
    public ItemVO queryById(String itemId, boolean useCache) {
        ItemDAORequest itemDAORequest = new ItemDAORequest();
        ItemVO itemVO = new ItemVO();

        if (useCache) {
            itemVO = itemFetchService.fetchFromCache(itemId);
            if (itemVO != null) {
                return itemVO;
            }
        }

        itemDAORequest.setItemId(itemId);
        itemVO = ViewObjectConverter.toViewObject(itemDAO.queryById(itemDAORequest));
        composeNecessaryInfo(itemVO);

        itemFetchService.putToCache(itemVO);

        return itemVO;
    }

    @Override
    public void refreshCache(List<String> itemIds, boolean refreshAll) {
        List<ItemVO> itemVOS = queryAllItem(refreshAll);
        if (itemVOS == null) {
            itemVOS = itemIds.stream().map(itemId -> {
                ItemDAORequest daoRequest = new ItemDAORequest();
                daoRequest.setItemId(itemId);
                return ViewObjectConverter.toViewObject(itemDAO.queryById(daoRequest));
            }).collect(Collectors.toList());
        }

        itemVOS.forEach(itemVO -> itemFetchService.putToCache(itemVO));
    }

    private void composeNecessaryInfo(ItemVO itemVO) {
        if (itemVO == null) {
            return;
        }

        String merchantId = itemVO.getMerchantInfo().getUserId();
        String hobbyId = itemVO.getHobby().getHobbyId();
        String categoryId = itemVO.getItemCategory().getCategoryId();
        String userLevel = itemVO.getUserLevel().getInterestLevelId();
        String merchantLevel = itemVO.getMerchantLevel().getInterestLevelId();

        itemVO.setMerchantInfo(userService.queryById(merchantId, true));
        itemVO.setItemCategory(itemCategoryService.query(categoryId, DatabaseConst.CATEGORY_ID));
        itemVO.setUserLevel(interestLevelService.query(userLevel, DatabaseConst.INTEREST_LEVEL_ID));
        itemVO.setMerchantLevel(interestLevelService.query(merchantLevel, DatabaseConst.INTEREST_LEVEL_ID));
        itemVO.setHobby(hobbyService.query(hobbyId, DatabaseConst.HOBBY_ID));
    }

    private List<ItemVO> queryAllItem(boolean refreshAll) {
        if (!refreshAll) {
            return null;
        }
        int count = itemDAO.count();
        ItemDAORequest daoRequest = new ItemDAORequest();
        PagingContext pagingContext = new PagingContext();
        pagingContext.setNumberOfItem(count);
        pagingContext.setPageNumber(1);

        daoRequest.setPagingContext(pagingContext);

        return itemDAO.queryAll(daoRequest).stream()
                .map(ViewObjectConverter::toViewObject).collect(Collectors.toList());
    }
}
