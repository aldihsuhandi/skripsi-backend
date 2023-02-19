package id.thesis.shumishumi.web.controller;

import id.thesis.shumishumi.facade.api.ItemFacade;
import id.thesis.shumishumi.web.callback.ControllerCallback;
import id.thesis.shumishumi.web.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.facade.request.HtmlRequest;
import id.thesis.shumishumi.facade.request.item.CreateItemRequest;
import id.thesis.shumishumi.facade.request.item.ItemApprovalRequest;
import id.thesis.shumishumi.facade.request.item.QueryItemRequest;
import id.thesis.shumishumi.facade.request.item.UpdateItemRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.item.CreateItemResult;
import id.thesis.shumishumi.facade.result.item.ItemApprovalResult;
import id.thesis.shumishumi.facade.result.item.QueryItemResult;
import id.thesis.shumishumi.facade.result.item.UpdateItemResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController extends BaseController {

    @Autowired
    private ItemFacade itemFacade;


    @PostMapping("/create")
    public CreateItemResult create(@RequestBody HtmlRequest<CreateItemRequest> request) {
        return (CreateItemResult) ControllerCallbackSupport.process(request.getHead(), request.getBody(), new ControllerCallback() {
            @Override
            public void authCheck() {
                authenticate(request.getHead());
            }

            @Override
            public BaseResult initResult() {
                return new CreateItemResult();
            }

            @Override
            public BaseResult doProcess() {
                return itemFacade.create(request.getBody());
            }
        });
    }

    @PostMapping("/query")
    public QueryItemResult query(@RequestBody HtmlRequest<QueryItemRequest> request) {
        return (QueryItemResult) ControllerCallbackSupport.process(request.getHead(), request.getBody(), new ControllerCallback() {
            @Override
            public void authCheck() {
                authenticate(request.getHead());
            }

            @Override
            public BaseResult initResult() {
                return new QueryItemResult();
            }

            @Override
            public BaseResult doProcess() {
                return itemFacade.query(request.getBody());
            }
        });
    }

    @PostMapping("/update")
    public UpdateItemResult update(@RequestBody HtmlRequest<UpdateItemRequest> request) {
        return (UpdateItemResult) ControllerCallbackSupport.process(request.getHead(), request.getBody(), new ControllerCallback() {
            @Override
            public void authCheck() {
                authenticate(request.getHead());
            }

            @Override
            public BaseResult initResult() {
                return new UpdateItemResult();
            }

            @Override
            public BaseResult doProcess() {
                return null;
            }
        });
    }

    @PostMapping("/approve")
    public ItemApprovalResult approve(@RequestBody HtmlRequest<ItemApprovalRequest> request) {
        return (ItemApprovalResult) ControllerCallbackSupport.process(request.getHead(), request.getBody(), new ControllerCallback() {
            @Override
            public void authCheck() {
                authenticate(request.getHead());
            }

            @Override
            public BaseResult initResult() {
                return new ItemApprovalResult();
            }

            @Override
            public BaseResult doProcess() {
                return itemFacade.approve(request.getBody());
            }
        });
    }
}
