package id.thesis.shumishumi.rest.controller;

import id.thesis.shumishumi.core.facade.ItemFacade;
import id.thesis.shumishumi.process.callback.ControllerCallback;
import id.thesis.shumishumi.process.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.rest.request.HtmlRequest;
import id.thesis.shumishumi.rest.request.item.CreateItemRequest;
import id.thesis.shumishumi.rest.request.item.QueryItemRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import id.thesis.shumishumi.rest.result.item.CreateItemResult;
import id.thesis.shumishumi.rest.result.item.QueryItemResult;
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
}
