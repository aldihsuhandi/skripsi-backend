package id.thesis.shumishumi.core.processor.item;

import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.ItemWishlistService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.converter.SummaryConverter;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.summary.ItemSummary;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.item.QueryItemDetailRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.item.QueryItemDetailResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class QueryItemDetailProcessor implements BaseProcessor {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemWishlistService itemWishlistService;

    @Autowired
    private SessionService sessionService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        QueryItemDetailRequest request = (QueryItemDetailRequest) baseRequest;
        QueryItemDetailResult result = (QueryItemDetailResult) baseResult;

        ItemVO itemVO = itemService.queryById(request.getItemId(), true);
        AssertUtil.isNotNull(itemVO, "item doesn't exist", ShumishumiErrorCodeEnum.ITEM_NOT_FOUND);
        AssertUtil.isExpected(!itemVO.isDeleted(), "item not found", ShumishumiErrorCodeEnum.ITEM_NOT_FOUND);

        String userId = "";
        if (StringUtils.isNotEmpty(request.getSessionId())) {
            SessionVO session = sessionService.query(request.getSessionId());
            userId = session != null ? session.getUserId() : "";
        }

        ItemSummary itemSummary = SummaryConverter.toSummary(itemVO,
                itemWishlistService.countItemWishlist(itemVO.getItemId()));
        itemSummary.setInWishlist(itemWishlistService.
                checkItemInWishlist(userId, itemSummary.getItemId()));

        result.setItem(itemSummary);
    }
}
