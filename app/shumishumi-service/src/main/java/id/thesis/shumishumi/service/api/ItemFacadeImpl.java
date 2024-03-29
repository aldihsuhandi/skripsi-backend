/**
 *
 */
package id.thesis.shumishumi.service.api;

import id.thesis.shumishumi.core.callback.ProcessCallback;
import id.thesis.shumishumi.core.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.facade.api.ItemFacade;
import id.thesis.shumishumi.facade.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.facade.request.item.AutocompleteItemRequest;
import id.thesis.shumishumi.facade.request.item.CreateItemRequest;
import id.thesis.shumishumi.facade.request.item.DeleteItemRequest;
import id.thesis.shumishumi.facade.request.item.ItemApprovalRequest;
import id.thesis.shumishumi.facade.request.item.QueryItemDetailRequest;
import id.thesis.shumishumi.facade.request.item.QueryItemRequest;
import id.thesis.shumishumi.facade.request.item.RecommendRequest;
import id.thesis.shumishumi.facade.request.item.UpdateItemRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.item.AutocompleteItemResult;
import id.thesis.shumishumi.facade.result.item.CreateItemResult;
import id.thesis.shumishumi.facade.result.item.DeleteItemResult;
import id.thesis.shumishumi.facade.result.item.ItemApprovalResult;
import id.thesis.shumishumi.facade.result.item.QueryItemDetailResult;
import id.thesis.shumishumi.facade.result.item.QueryItemResult;
import id.thesis.shumishumi.facade.result.item.RecommendResult;
import id.thesis.shumishumi.facade.result.item.UpdateItemResult;
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
    public QueryItemDetailResult queryDetail(QueryItemDetailRequest request) {
        return (QueryItemDetailResult) ProcessCallbackSupport.process(ProcessTypeEnum.ITEM_QUERY_DETAIL, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new QueryItemDetailResult();
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
    public DeleteItemResult delete(DeleteItemRequest request) {
        return (DeleteItemResult) ProcessCallbackSupport.process(ProcessTypeEnum.ITEM_DELETE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new DeleteItemResult();
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
