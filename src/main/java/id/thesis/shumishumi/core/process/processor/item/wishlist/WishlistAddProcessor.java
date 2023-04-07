package id.thesis.shumishumi.core.process.processor.item.wishlist;

import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.core.process.processor.BaseProcessor;
import id.thesis.shumishumi.core.service.ItemService;
import id.thesis.shumishumi.core.service.ItemWishlistService;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.item.wishlist.AddWishlistRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

public class WishlistAddProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemWishlistService itemWishlistService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        AddWishlistRequest request = (AddWishlistRequest) baseRequest;

        SessionVO sessionVO = sessionService.query(request.getSessionId());
        ItemVO itemVO = itemService.queryById(request.getItemId(), true);

        if (sessionVO == null || itemVO == null) {
            return;
        }

        itemWishlistService.addWishlist(sessionVO.getUserId(), itemVO.getItemId());
    }
}
