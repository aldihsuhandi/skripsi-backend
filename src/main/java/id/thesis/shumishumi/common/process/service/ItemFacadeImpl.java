/**
 *
 */
package id.thesis.shumishumi.common.process.service;

import id.thesis.shumishumi.common.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.common.process.callback.ProcessCallback;
import id.thesis.shumishumi.common.process.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.core.facade.ItemFacade;
import id.thesis.shumishumi.core.request.item.AutocompleteItemRequest;
import id.thesis.shumishumi.core.request.item.CreateItemRequest;
import id.thesis.shumishumi.core.request.item.ItemApprovalRequest;
import id.thesis.shumishumi.core.request.item.QueryItemRequest;
import id.thesis.shumishumi.core.request.item.RecommendRequest;
import id.thesis.shumishumi.core.request.item.UpdateItemRequest;
import id.thesis.shumishumi.core.request.item.image.ItemImageAddRequest;
import id.thesis.shumishumi.core.request.item.image.ItemImageRemoveRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.result.item.AutocompleteItemResult;
import id.thesis.shumishumi.core.result.item.CreateItemResult;
import id.thesis.shumishumi.core.result.item.ItemApprovalResult;
import id.thesis.shumishumi.core.result.item.QueryItemResult;
import id.thesis.shumishumi.core.result.item.RecommendResult;
import id.thesis.shumishumi.core.result.item.UpdateItemResult;
import id.thesis.shumishumi.core.result.item.image.ItemImageAddResult;
import id.thesis.shumishumi.core.result.item.image.ItemImageRemoveResult;
import org.springframework.stereotype.Service;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ItemFacadeImpl.java, v 0.1 2023‐01‐16 4:25 PM Aldih Suhandi Exp $$
 */
@Service
public class ItemFacadeImpl extends ProcessFacade implements ItemFacade {
    @Override
    public CreateItemResult create(CreateItemRequest request) {
        return (CreateItemResult) ProcessCallbackSupport.process(ProcessTypeEnum.ITEM_CREATE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new CreateItemResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public QueryItemResult query(QueryItemRequest request) {
        return (QueryItemResult) ProcessCallbackSupport.process(ProcessTypeEnum.ITEM_QUERY, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new QueryItemResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public UpdateItemResult update(UpdateItemRequest request) {
        return (UpdateItemResult) ProcessCallbackSupport.process(ProcessTypeEnum.ITEM_UPDATE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new UpdateItemResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public ItemImageAddResult addImage(ItemImageAddRequest request) {
        return (ItemImageAddResult) ProcessCallbackSupport.process(ProcessTypeEnum.ITEM_IMAGE_ADD, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new ItemImageAddResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public ItemImageRemoveResult removeImage(ItemImageRemoveRequest request) {
        return (ItemImageRemoveResult) ProcessCallbackSupport.process(ProcessTypeEnum.ITEM_IMAGE_REMOVE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new ItemImageRemoveResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public ItemApprovalResult approve(ItemApprovalRequest request) {
        return (ItemApprovalResult) ProcessCallbackSupport.process(ProcessTypeEnum.ITEM_APPROVAL, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new ItemApprovalResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public RecommendResult recommend(RecommendRequest request) {
        return (RecommendResult) ProcessCallbackSupport.process(ProcessTypeEnum.RECOMMEND, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new RecommendResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public AutocompleteItemResult autocomplete(AutocompleteItemRequest request) {
        return (AutocompleteItemResult) ProcessCallbackSupport.process(ProcessTypeEnum.AUTOCOMPLETE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new AutocompleteItemResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }
}
