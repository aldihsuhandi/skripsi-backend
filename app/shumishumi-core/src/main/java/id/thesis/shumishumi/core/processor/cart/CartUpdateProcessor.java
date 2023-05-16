package id.thesis.shumishumi.core.processor.cart;

import id.thesis.shumishumi.common.service.CartService;
import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.cart.CartUpdateRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

public class CartUpdateProcessor implements BaseProcessor {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CartService cartService;

    @Autowired
    private SessionService sessionService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        CartUpdateRequest request = (CartUpdateRequest) baseRequest;

        String userId = sessionService.query(request.getSessionId()).getUserId();

        ItemVO item = itemService.queryById(request.getItemId(), true);
        AssertUtil.isNotNull(item, "item not found", ShumishumiErrorCodeEnum.ITEM_NOT_FOUND);
        AssertUtil.isExpected(!item.isDeleted(), "item not found", ShumishumiErrorCodeEnum.ITEM_NOT_FOUND);
        AssertUtil.isExpected(item.getItemQuantity() >= request.getQuantity(), "item quantity is not enough", ShumishumiErrorCodeEnum.ITEM_NOT_ENOUGH);

        cartService.update(userId, item.getItemId(), request.getQuantity());
    }
}
