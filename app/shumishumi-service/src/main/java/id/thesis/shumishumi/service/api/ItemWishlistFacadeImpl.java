package id.thesis.shumishumi.service.api;

import id.thesis.shumishumi.core.callback.ProcessCallback;
import id.thesis.shumishumi.core.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.facade.api.ItemWishlistFacade;
import id.thesis.shumishumi.facade.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.facade.request.item.wishlist.AddWishlistRequest;
import id.thesis.shumishumi.facade.request.item.wishlist.QueryWishlistRequest;
import id.thesis.shumishumi.facade.request.item.wishlist.RemoveWishlistRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.item.wishlist.AddWishlistResult;
import id.thesis.shumishumi.facade.result.item.wishlist.QueryWishlistResult;
import id.thesis.shumishumi.facade.result.item.wishlist.RemoveWishlistResult;
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
