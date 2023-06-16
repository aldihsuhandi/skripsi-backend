/**
 *
 */
package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.request.item.CreateItemInnerRequest;
import id.thesis.shumishumi.common.service.HobbyService;
import id.thesis.shumishumi.common.service.InterestLevelService;
import id.thesis.shumishumi.common.service.ItemCategoryService;
import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.KnowledgeService;
import id.thesis.shumishumi.common.service.ReviewService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.common.util.comparator.ItemVOComparator;
import id.thesis.shumishumi.core.converter.ViewObjectConverter;
import id.thesis.shumishumi.core.fetch.ItemFetchService;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.facade.model.context.ItemFilterContext;
import id.thesis.shumishumi.facade.model.context.ItemUpdateContext;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.context.SortingContext;
import id.thesis.shumishumi.facade.model.viewobject.HistoryItemVO;
import id.thesis.shumishumi.facade.model.viewobject.HobbyVO;
import id.thesis.shumishumi.facade.model.viewobject.InterestLevelVO;
import id.thesis.shumishumi.facade.model.viewobject.ItemCategoryVO;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.model.viewobject.ReviewVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.foundation.converter.ItemDAORequestConverter;
import id.thesis.shumishumi.foundation.model.request.ItemDAORequest;
import id.thesis.shumishumi.foundation.model.result.HistoryItemDO;
import id.thesis.shumishumi.foundation.model.result.ItemDO;
import id.thesis.shumishumi.foundation.service.ItemDAO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
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

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private KnowledgeService knowledgeService;

    @Override
    public void create(CreateItemInnerRequest request) {
        ItemDO item = ItemDAORequestConverter.toDAORequest(request);
        itemDAO.create(item);
    }

    @Override
    public String createHistoryItem(ItemVO item) {
        StringBuilder imageBuilder = new StringBuilder();
        item.getItemImages().forEach(image ->
                imageBuilder.append(image).append(CommonConst.SEPARATOR));
        String imageStr = "";
        if (imageBuilder.length() != 0) {
            imageStr = imageBuilder.substring(0, imageBuilder.length() - 1);
        }

        String historyItemId = FunctionUtil.generateUUID();
        HistoryItemDO history = new HistoryItemDO();
        history.setHistoryItemId(historyItemId);
        history.setItemId(item.getItemId());
        history.setItemName(item.getItemName());
        history.setItemPrice(item.getItemPrice());
        history.setItemDescription(item.getItemDescription());
        history.setItemImages(imageStr);
        history.setCategoryId(item.getItemCategory().getCategoryId());
        history.setHobbyId(item.getHobby().getHobbyId());
        history.setMerchantId(item.getMerchantInfo().getUserId());
        history.setMerchantLevelId(item.getMerchantLevel().getInterestLevelId());
        if (item.getUserLevel() != null) {
            history.setUserLevelId(item.getUserLevel().getInterestLevelId());
        }

        itemDAO.createItemHistory(history);

        return historyItemId;
    }

    @Override
    public HistoryItemVO queryItemHistory(String historyItemId) {
        HistoryItemVO history = ViewObjectConverter.toViewObject(
                itemDAO.queryItemHistory(historyItemId));
        composeNecessaryInfo(history.getItem());

        return history;
    }

    @Override
    public ItemVO queryById(String itemId, boolean useCache) {
        ItemDAORequest itemDAORequest = new ItemDAORequest();
        ItemVO itemVO = new ItemVO();

        if (useCache) {
            itemVO = itemFetchService.fetchFromCache(itemId);
            if (itemVO != null) {
                itemVO.setMerchantInfo(userService.queryById(
                        itemVO.getMerchantInfo().getUserId(), true));
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
    public List<ItemVO> queryList(ItemFilterContext itemFilterContext, SortingContext sortingContext,
                                  PagingContext pagingContext, boolean useCache) {
        if (StringUtils.isNotEmpty(itemFilterContext.getMerchantEmail())) {
            UserVO merchantInfo = userService.queryByEmail(itemFilterContext.getMerchantEmail(), true);
            itemFilterContext.setMerchantId(merchantInfo.getUserId());
        }

        if (useCache) {
            List<ItemVO> itemVOS = queryListFromCache(itemFilterContext, sortingContext,
                    pagingContext.getPageNumber(), pagingContext.getNumberOfItem());
            if (!itemVOS.isEmpty()) {
                pagingContext.setTotalItem((long) countWithFilter(itemFilterContext));
                return itemVOS;
            }
        }

        ItemCategoryVO category = itemCategoryService.query(itemFilterContext.getItemCategory(), DatabaseConst.CATEGORY_NAME);
        InterestLevelVO merchantLevel = interestLevelService.query(itemFilterContext.getMerchantInterestLevel(), DatabaseConst.INTEREST_LEVEL_NAME);
        InterestLevelVO userLevel = interestLevelService.query(itemFilterContext.getUserInterestLevel(), DatabaseConst.INTEREST_LEVEL_NAME);
        HobbyVO hobby = hobbyService.query(itemFilterContext.getHobby(), DatabaseConst.HOBBY_NAME);

        ItemDAORequest daoRequest = ItemDAORequestConverter.toDAORequest(
                itemFilterContext,
                sortingContext,
                category == null ? "" : category.getCategoryId(),
                hobby == null ? "" : hobby.getHobbyId(),
                merchantLevel == null ? "" : merchantLevel.getInterestLevelId(),
                userLevel == null ? "" : userLevel.getInterestLevelId()
        );

        daoRequest.setPagingContext(pagingContext);

        return itemDAO.query(daoRequest).stream().map(itemDO -> {
            ItemVO itemVO = ViewObjectConverter.toViewObject(itemDO);
            composeNecessaryInfo(itemVO);
            itemFetchService.putToCache(itemVO);

            return itemVO;
        }).collect(Collectors.toList());
    }

    @Override
    public void update(ItemVO itemVO, ItemUpdateContext updateContext, List<String> images) {
        FunctionUtil.fillEmptyUpdateContext(updateContext, itemVO);

        itemVO.setItemImages(images);

        HobbyVO hobby = hobbyService.query(updateContext.getHobbyName(), DatabaseConst.HOBBY_NAME);
        ItemCategoryVO category = itemCategoryService.query(updateContext.getCategoryName(), DatabaseConst.CATEGORY_NAME);
        InterestLevelVO merchantLevel = interestLevelService.query(updateContext.getMerchantInterestLevel(), DatabaseConst.INTEREST_LEVEL_NAME);

        ItemDO item = ItemDAORequestConverter.toDAORequest(itemVO, updateContext, category.getCategoryId(),
                hobby.getHobbyId(), merchantLevel.getInterestLevelId(), itemVO.getItemId());
        itemDAO.update(item);
    }

    @Override
    public void delete(String itemId) {
        itemDAO.softDelete(itemId);
    }

    @Override
    public void updatePicture(String itemId, List<String> itemImages) {

        StringBuilder imageStr = new StringBuilder();
        itemImages.forEach(image ->
                imageStr.append(image).append(CommonConst.SEPARATOR));

        String itemImagesStr = "";
        if (imageStr.length() != 0) {
            itemImagesStr = imageStr.substring(0, imageStr.length() - 1);
        }

        ItemDAORequest daoRequest = new ItemDAORequest();
        daoRequest.setItemId(itemId);
        daoRequest.setItemImages(itemImagesStr);

        itemDAO.updateImage(daoRequest);
    }

    @Override
    public void approveItem(String itemId) {
        ItemDAORequest daoRequest = ItemDAORequestConverter.toDAORequest(itemId);
        itemDAO.approve(daoRequest);
    }

    @Override
    public List<ItemVO> fetchAll() {
        return itemFetchService.fetchAll();
    }

    @Override
    public void refreshCache(List<String> itemIds, boolean refreshAll) {
        List<ItemVO> itemVOS = queryAllItem(refreshAll);
        if (itemVOS == null) {
            itemVOS = itemIds.stream().map(itemId -> {
                ItemDAORequest daoRequest = new ItemDAORequest();
                daoRequest.setItemId(itemId);

                ItemVO itemVO = ViewObjectConverter.toViewObject(itemDAO.queryById(daoRequest));
                composeNecessaryInfo(itemVO);

                return itemVO;
            }).collect(Collectors.toList());
        }

        itemVOS.forEach(itemVO -> itemFetchService.putToCache(itemVO));
    }

    @Override
    public void clearCache() {
        itemFetchService.clearCache();
    }

    @Override
    public void calculateUserReview(String itemId) {
        ItemVO itemVO = this.queryById(itemId, true);
        knowledgeService.removeItemFromKnowledge(itemVO);

        List<ReviewVO> reviews = reviewService.queryByItemId(itemId);
        Map<String, Long> countMap = reviews.stream().collect(Collectors.groupingBy(review ->
                review.getInterestLevel().getInterestLevelId(), Collectors.counting()));

        String interestLevel = Collections.max(countMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        double reviewNumber = reviews.stream().mapToInt(ReviewVO::getStar).average().orElse(0.0);

        itemDAO.updateUserLevel(itemId, interestLevel);
        itemDAO.updateItemReview(itemId, reviewNumber);
        this.refreshCache(Collections.singletonList(itemId), false);

        itemVO = this.queryById(itemId, true);
        knowledgeService.addItemToKnowledge(itemVO);
    }

    @Override
    public int count(ItemFilterContext itemFilterContext, boolean useCache) {
        if (useCache) {
            return countWithFilter(itemFilterContext);
        }
        return itemDAO.count();
    }

    @Override
    public List<String> autocomplete(String itemName, boolean useCache) {
        if (useCache) {
            return itemFetchService.fetchAll().stream().map(ItemVO::getItemName).
                    filter(name -> name.toLowerCase().contains(StringUtils.lowerCase(itemName))).collect(Collectors.toList());
        }

        ItemDAORequest request = new ItemDAORequest();
        request.setItemName(itemName);

        return itemDAO.autocomplete(request);
    }

    private void composeNecessaryInfo(ItemVO itemVO) {
        if (itemVO == null) {
            return;
        }

        String itemId = itemVO.getItemId();
        String merchantId = itemVO.getMerchantInfo().getUserId();
        String hobbyId = itemVO.getHobby().getHobbyId();
        String categoryId = itemVO.getItemCategory().getCategoryId();
        String merchantLevel = itemVO.getMerchantLevel().getInterestLevelId();
        String userLevel = itemVO.getUserLevel() == null ? "" :
                itemVO.getUserLevel().getInterestLevelId();

        itemVO.setMerchantInfo(userService.queryById(merchantId, true));
        itemVO.setItemCategory(itemCategoryService.query(categoryId, DatabaseConst.CATEGORY_ID));
        itemVO.setMerchantLevel(interestLevelService.query(merchantLevel, DatabaseConst.INTEREST_LEVEL_ID));
        itemVO.setHobby(hobbyService.query(hobbyId, DatabaseConst.HOBBY_ID));

        if (StringUtils.isNotEmpty(userLevel)) {
            itemVO.setUserLevel(interestLevelService.query(userLevel, DatabaseConst.INTEREST_LEVEL_ID));
        }
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
                .map(itemDO -> {
                    ItemVO itemVO = ViewObjectConverter.toViewObject(itemDO);
                    composeNecessaryInfo(itemVO);

                    return itemVO;
                }).collect(Collectors.toList());
    }

    private List<ItemVO> queryListFromCache(ItemFilterContext itemFilterContext, SortingContext sortingContext, int page, int numberOfItems) {
        ItemVOComparator itemVOComparator = new ItemVOComparator(sortingContext.getSorting(), sortingContext.getSortingType());
        List<ItemVO> itemVOS = itemFetchService.fetchAll().stream()
                .sorted(itemVOComparator)
                .filter(itemVO -> FunctionUtil.itemFilter(itemVO, itemFilterContext)).collect(Collectors.toList());

        PagingContext pagingContext = new PagingContext();
        pagingContext.setPageNumber(page);
        pagingContext.setNumberOfItem(numberOfItems);

        List<ItemVO> result = new ArrayList<>();
        if (itemVOS.size() < pagingContext.calculateOffset() || itemVOS.isEmpty()) {
            return new ArrayList<>();
        }

        int sz = Math.min(pagingContext.getNumberOfItem() + pagingContext.calculateOffset(), itemVOS.size());
        for (int i = Math.max(pagingContext.calculateOffset(), 0); i < sz; ++i) {
            result.add(itemVOS.get(i));
        }

        return result;
    }

    private int countWithFilter(ItemFilterContext itemFilterContext) {
        List<ItemVO> itemVOS = itemFetchService.fetchAll().stream().
                filter(itemVO -> FunctionUtil.itemFilter(itemVO, itemFilterContext)).collect(Collectors.toList());

        return itemVOS.size();
    }
}
