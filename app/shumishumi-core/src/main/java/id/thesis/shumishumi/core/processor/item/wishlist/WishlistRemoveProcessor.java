package id.thesis.shumishumi.core.processor.item.wishlist;

import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.ItemWishlistService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.item.wishlist.RemoveWishlistRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

public class WishlistRemoveProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemWishlistService itemWishlistService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        RemoveWishlistRequest request = (RemoveWishlistRequest) baseRequest;

        SessionVO sessionVO = sessionService.query(baseRequest.getSessionId());
        ItemVO itemVO = itemService.queryById(request.getItemId(), true);
        if (sessionVO == null || itemVO == null) {
            return;
        }

        itemWishlistService.removeWishlist(sessionVO.getUserId(), itemVO.getItemId());
    }
}
