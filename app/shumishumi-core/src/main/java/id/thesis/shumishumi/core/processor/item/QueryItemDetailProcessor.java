package id.thesis.shumishumi.core.processor.item;

import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.ItemWishlistService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.converter.SummaryConverter;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.summary.ItemSummary;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.item.QueryItemDetailRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.item.QueryItemDetailResult;
import org.springframework.beans.factory.annotation.Autowired;

public class QueryItemDetailProcessor implements BaseProcessor {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemWishlistService itemWishlistService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        QueryItemDetailRequest request = (QueryItemDetailRequest) baseRequest;
        QueryItemDetailResult result = (QueryItemDetailResult) baseResult;

        ItemVO itemVO = itemService.queryById(request.getItemId(), true);
        AssertUtil.isNotNull(itemVO, "item doesn't exist", ShumishumiErrorCodeEnum.ITEM_NOT_FOUND);

        ItemSummary itemSummary = SummaryConverter.toSummary(itemVO);
        itemSummary.setTotalWishlist(itemWishlistService.countItemWishlist(itemVO.getItemId()));

        result.setItem(itemSummary);
    }
}
