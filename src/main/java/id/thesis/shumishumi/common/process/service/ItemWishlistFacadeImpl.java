package id.thesis.shumishumi.common.process.service;

import id.thesis.shumishumi.common.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.core.facade.ItemWishlistFacade;
import id.thesis.shumishumi.common.process.callback.ProcessCallback;
import id.thesis.shumishumi.common.process.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.core.request.item.wishlist.AddWishlistRequest;
import id.thesis.shumishumi.core.request.item.wishlist.QueryWishlistRequest;
import id.thesis.shumishumi.core.request.item.wishlist.RemoveWishlistRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.result.item.wishlist.AddWishlistResult;
import id.thesis.shumishumi.core.result.item.wishlist.QueryWishlistResult;
import id.thesis.shumishumi.core.result.item.wishlist.RemoveWishlistResult;
import org.springframework.stereotype.Service;

@Service
public class ItemWishlistFacadeImpl extends ProcessFacade implements ItemWishlistFacade {
    @Override
    public AddWishlistResult add(AddWishlistRequest request) {
        return (AddWishlistResult) ProcessCallbackSupport.process(ProcessTypeEnum.WISHLIST_ADD, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new AddWishlistResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public RemoveWishlistResult remove(RemoveWishlistRequest request) {
        return (RemoveWishlistResult) ProcessCallbackSupport.process(ProcessTypeEnum.WISHLIST_REMOVE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new RemoveWishlistResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public QueryWishlistResult query(QueryWishlistRequest request) {
        return (QueryWishlistResult) ProcessCallbackSupport.process(ProcessTypeEnum.WISHLIST_QUERY, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new QueryWishlistResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }
}
