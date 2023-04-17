/**
 *
 */
package id.thesis.shumishumi.common.process.processor.item;

import id.thesis.shumishumi.common.model.context.ItemFilterContext;
import id.thesis.shumishumi.common.model.context.PagingContext;
import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.common.process.processor.BaseProcessor;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.item.QueryItemRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.result.item.QueryItemResult;
import id.thesis.shumishumi.core.service.ItemService;
import id.thesis.shumishumi.core.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: QueryItemProcessor.java, v 0.1 2023‐01‐19 9:09 AM Aldih Suhandi Exp $$
 */
public class QueryItemProcessor implements BaseProcessor {

    @Autowired
    private ItemService itemService;

    @Autowired
    private SessionService sessionService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        QueryItemRequest queryRequest = (QueryItemRequest) baseRequest;
        QueryItemResult queryResult = (QueryItemResult) baseResult;

        List<ItemVO> itemVOS = new ArrayList<>();
        queryById(queryRequest, itemVOS);

        if (itemVOS.isEmpty()) {
            int page = queryRequest.getPageNumber();
            int numberOfItems = queryRequest.getNumberOfItem();

            itemVOS = queryItemList(queryRequest, page, numberOfItems);
        }

        composeResult(queryRequest, queryResult, itemVOS);
    }

    private void composeResult(QueryItemRequest request, QueryItemResult result, List<ItemVO> itemVOS) {
        Long count = (long) itemService.count(request.getItemFilterContext(), true);

        PagingContext pagingContext = new PagingContext(request.getPageNumber(), request.getNumberOfItem(), (long) count);
        pagingContext.checkHasNext(count, itemVOS.size());

        result.setItems(itemVOS);
        result.setPagingContext(pagingContext);
    }

    private List<ItemVO> queryItemList(QueryItemRequest request, int page, int numberOfItems) {
        ItemFilterContext filterContext = request.getItemFilterContext();
        return itemService.queryList(filterContext, page, numberOfItems, true);
    }

    private void queryById(QueryItemRequest request, List<ItemVO> itemVOS) {
        if (request.getItemFilterContext().getItemId() == null || request.getItemFilterContext().getItemId().isEmpty()) {
            return;
        }

        ItemFilterContext filterContext = request.getItemFilterContext();

        ItemVO itemVO = itemService.queryById(filterContext.getItemId(), true);
        itemVOS.add(itemVO);
    }
}
