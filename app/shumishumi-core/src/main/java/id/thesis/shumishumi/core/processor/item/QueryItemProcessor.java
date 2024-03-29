/**
 *
 */
package id.thesis.shumishumi.core.processor.item;

import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.ItemWishlistService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.core.converter.SummaryConverter;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.context.ItemFilterContext;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.context.SortingContext;
import id.thesis.shumishumi.facade.model.summary.ItemSummary;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.item.QueryItemRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.item.QueryItemResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: QueryItemProcessor.java, v 0.1 2023‐01‐19 9:09 AM Aldih Suhandi Exp $$
 */
public class QueryItemProcessor implements BaseProcessor {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemWishlistService itemWishlistService;

    @Autowired
    private SessionService sessionService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        QueryItemRequest queryRequest = (QueryItemRequest) baseRequest;
        QueryItemResult queryResult = (QueryItemResult) baseResult;

        int page = queryRequest.getPageNumber();
        int numberOfItems = queryRequest.getNumberOfItem();

        PagingContext pagingContext = new PagingContext();
        pagingContext.setPageNumber(page);
        pagingContext.setNumberOfItem(numberOfItems);
        pagingContext.setTotalItem(1L);

        List<ItemVO> itemVOS = new ArrayList<>();
        itemVOS = queryItemList(queryRequest, pagingContext);

        String userId;
        if (StringUtils.isNotEmpty(queryRequest.getSessionId())) {
            SessionVO session = sessionService.query(queryRequest.getSessionId());
            userId = session != null ? session.getUserId() : "";
        } else {
            userId = "";
        }

        pagingContext.calculateTotalPage();
        pagingContext.checkHasNext(pagingContext.getTotalItem(), pagingContext.getNumberOfItem());

        queryResult.setItems(itemVOS.stream().
                map(itemVO -> {
                    int totalWishlist = itemWishlistService.countItemWishlist(itemVO.getItemId());
                    ItemSummary itemSummary = SummaryConverter.toSummary(itemVO, totalWishlist);
                    itemSummary.setInWishlist(itemWishlistService.checkItemInWishlist(userId, itemSummary.getItemId()));

                    return itemSummary;
                }).collect(Collectors.toList()));
        queryResult.setPagingContext(pagingContext);
    }

    private List<ItemVO> queryItemList(QueryItemRequest request, PagingContext pagingContext) {
        ItemFilterContext filterContext = request.getItemFilterContext();
        SortingContext sortingContext = request.getSortingContext();
        return itemService.queryList(filterContext, sortingContext, pagingContext, true);
    }
}
