package id.thesis.shumishumi.core.processor.item;

import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.ItemWishlistService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.item.RecommendRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.item.RecommendResult;
import org.springframework.beans.factory.annotation.Autowired;

public class RecommendProcessor implements BaseProcessor {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemWishlistService itemWishlistService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        RecommendResult result = (RecommendResult) baseResult;
        RecommendRequest request = (RecommendRequest) baseRequest;
    }
}
