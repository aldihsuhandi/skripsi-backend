/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.process.processor.item;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.model.context.ItemFilterContext;
import id.thesis.shumishumi.common.model.context.PagingContext;
import id.thesis.shumishumi.common.model.enumeration.ActivityEnum;
import id.thesis.shumishumi.common.model.viewobject.ActivityVO;
import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.core.service.ActivityService;
import id.thesis.shumishumi.core.service.ItemImageService;
import id.thesis.shumishumi.core.service.ItemService;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.process.processor.BaseProcessor;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.item.QueryItemRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import id.thesis.shumishumi.rest.result.item.QueryItemResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: QueryItemProcessor.java, v 0.1 2023‐01‐19 9:09 AM Aldih Suhandi Exp $$
 */
public class QueryItemProcessor implements BaseProcessor {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ItemImageService itemImageService;

    @Override
    public void doProcess(BaseResult result, BaseRequest request) {
        QueryItemRequest queryRequest = (QueryItemRequest) request;
        QueryItemResult queryResult = (QueryItemResult) result;

        List<ItemVO> itemVOS = new ArrayList<>();
        queryById(queryRequest, itemVOS);

        if (!itemVOS.isEmpty()) {
            int page = queryRequest.getPageNumber();
            int numberOfItems = queryRequest.getNumberOfItem();

            queryItemList(queryRequest, itemVOS, page, numberOfItems);
        }

        composeResult(queryRequest, queryResult, itemVOS);
    }

    private void composeResult(QueryItemRequest request,
                               QueryItemResult result, List<ItemVO> itemVOS) {
        int count = itemService.count(true);

        PagingContext pagingContext = new PagingContext();
        pagingContext.setPageNumber(request.getPageNumber());
        pagingContext.setNumberOfItem(request.getNumberOfItem());
        pagingContext.checkHasNext(count, itemVOS.size());

        result.setItems(itemVOS);
        result.setPagingContext(pagingContext);
    }

    private void queryItemList(QueryItemRequest request, List<ItemVO> itemVOS, int page, int numberOfItems) {
        ItemFilterContext filterContext = request.getItemFilterContext();
        itemVOS = itemService.queryList(filterContext, page, numberOfItems, true);
    }

    private void queryById(QueryItemRequest request, List<ItemVO> itemVOS) {
        if (request.getItemFilterContext().getItemId() == null ||
                request.getItemFilterContext().getItemId().isEmpty()) {
            return;
        }

        ItemFilterContext filterContext = request.getItemFilterContext();

        ItemVO itemVO = itemService.queryById(filterContext.getItemId(), true);
        itemVOS.add(itemVO);

        createUserActivity(request.getSessionId(), request.getItemFilterContext().getItemId());
    }

    private void createUserActivity(String sessionId, String itemId) {
        if (sessionId == null || sessionId.isEmpty()) {
            return;
        }

        SessionVO sessionVO = sessionService.query(sessionId);
        ActivityVO activityVO = activityService.queryActivity(ActivityEnum.BUY.getName(), DatabaseConst.ACTIVITY_NAME);

        activityService.createUserActivity(sessionVO.getUserId(), itemId, activityVO.getActivityId());
    }
}
