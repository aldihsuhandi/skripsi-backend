/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.converter.ViewObjectConverter;
import id.thesis.shumishumi.common.model.context.ItemFilterContext;
import id.thesis.shumishumi.common.model.context.ItemUpdateContext;
import id.thesis.shumishumi.common.model.context.PagingContext;
import id.thesis.shumishumi.common.model.request.item.CreateItemInnerRequest;
import id.thesis.shumishumi.common.model.viewobject.*;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.fetch.ItemFetchService;
import id.thesis.shumishumi.core.service.*;
import id.thesis.shumishumi.dalgen.converter.ItemDAORequestConverter;
import id.thesis.shumishumi.dalgen.model.request.ItemDAORequest;
import id.thesis.shumishumi.dalgen.service.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<ItemVO> queryList(ItemFilterContext itemFilterContext,
                                  int page, int numberOfItem, boolean useCache) {
        List<ItemVO> itemVOS = new ArrayList<>();
        UserVO merchantInfo = userService.queryByEmail(itemFilterContext.getMerchantEmail(), true);

        itemFilterContext.setMerchantId(merchantInfo.getUserId());

        if (useCache) {
            return queryListFromCache(itemFilterContext, page, numberOfItem);
        }

        ItemCategoryVO category = itemCategoryService.query(itemFilterContext.getItemCategory(), DatabaseConst.CATEGORY_NAME);
        InterestLevelVO merchantLevel = interestLevelService.query(itemFilterContext.getMerchantInterestLevel(), DatabaseConst.INTEREST_LEVEL_ID);
        InterestLevelVO userLevel = interestLevelService.query(itemFilterContext.getUserInterestLevel(), DatabaseConst.INTEREST_LEVEL_ID);
        HobbyVO hobby = hobbyService.query(itemFilterContext.getHobby(), DatabaseConst.HOBBY_NAME);

        ItemDAORequest daoRequest = ItemDAORequestConverter.toDAORequest(itemFilterContext, category.getCategoryId(),
                hobby.getHobbyId(), merchantLevel.getInterestLevelId(), userLevel.getInterestLevelId());

        PagingContext pagingContext = new PagingContext();
        pagingContext.setNumberOfItem(numberOfItem);
        pagingContext.setPageNumber(page);

        daoRequest.setPagingContext(pagingContext);

        return itemDAO.query(daoRequest).stream().map(itemDO -> {
            ItemVO itemVO = ViewObjectConverter.toViewObject(itemDO);
            itemFetchService.putToCache(itemVO);

            return itemVO;
        }).collect(Collectors.toList());
    }

    @Override
    public void update(String itemId, ItemUpdateContext itemUpdateContext) {
        ItemVO itemVO = this.queryById(itemId, true);
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

    @Override
    public int count(boolean useCache) {
        return itemDAO.count();
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

    private List<ItemVO> queryListFromCache(ItemFilterContext itemFilterContext, int page, int numberOfItems) {
        List<ItemVO> itemVOS = itemFetchService.fetchAll().
                stream().filter(itemVO -> {
                    return FunctionUtil.itemFilter(itemVO, itemFilterContext);
                }).collect(Collectors.toList());

        PagingContext pagingContext = new PagingContext();
        pagingContext.setPageNumber(page);
        pagingContext.setNumberOfItem(numberOfItems);

        List<ItemVO> result = new ArrayList<>();
        if (itemVOS.size() < pagingContext.calculateOffset()) {
            return new ArrayList<>();
        }

        for (int i = pagingContext.calculateOffset() - 1;
             i < pagingContext.getNumberOfItem() + pagingContext.calculateOffset(); ++i) {
            result.add(itemVOS.get(i));
        }

        return result;
    }
}
