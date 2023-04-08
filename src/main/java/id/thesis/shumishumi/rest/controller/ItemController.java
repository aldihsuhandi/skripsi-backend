package id.thesis.shumishumi.rest.controller;

import id.thesis.shumishumi.common.process.callback.ControllerCallback;
import id.thesis.shumishumi.common.process.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.core.facade.ItemFacade;
import id.thesis.shumishumi.core.request.item.AutocompleteItemRequest;
import id.thesis.shumishumi.core.request.item.CreateItemRequest;
import id.thesis.shumishumi.core.request.item.QueryItemRequest;
import id.thesis.shumishumi.core.request.item.RecommendRequest;
import id.thesis.shumishumi.core.request.item.UpdateItemRequest;
import id.thesis.shumishumi.core.result.item.AutocompleteItemResult;
import id.thesis.shumishumi.core.result.item.CreateItemResult;
import id.thesis.shumishumi.core.result.item.QueryItemResult;
import id.thesis.shumishumi.core.result.item.RecommendResult;
import id.thesis.shumishumi.core.result.item.UpdateItemResult;
import id.thesis.shumishumi.rest.form.item.CreateItemForm;
import id.thesis.shumishumi.rest.form.item.ItemAutocompleteForm;
import id.thesis.shumishumi.rest.form.item.QueryItemForm;
import id.thesis.shumishumi.rest.form.item.RecommendForm;
import id.thesis.shumishumi.rest.form.item.UpdateItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController extends BaseController {

    @Autowired
    private ItemFacade itemFacade;

    @PostMapping("/create")
    public ResponseEntity<CreateItemResult> create(@RequestHeader HttpHeaders headers, @RequestBody CreateItemForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<CreateItemResult, CreateItemRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public CreateItemRequest composeRequest() {
                CreateItemRequest request = new CreateItemRequest();
                request.setItemName(form.getItemName());
                request.setItemPrice(form.getItemPrice());
                request.setItemDescription(form.getItemDescription());
                request.setItemQuantity(form.getItemQuantity());
                request.setCategoryName(form.getCategoryName());
                request.setHobbyName(form.getHobbyName());
                request.setMerchantInterestLevel(form.getMerchantInterestLevel());

                return request;
            }

            @Override
            public CreateItemResult doProcess(CreateItemRequest request) {
                return itemFacade.create(request);
            }
        });
    }

    @PostMapping("/query")
    public ResponseEntity<QueryItemResult> query(@RequestHeader HttpHeaders headers, @RequestBody QueryItemForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<QueryItemResult, QueryItemRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public QueryItemRequest composeRequest() {
                QueryItemRequest request = new QueryItemRequest();
                request.setItemFilterContext(form.getItemFilterContext());
                request.setNumberOfItem(form.getNumberOfItem());
                request.setPageNumber(form.getPageNumber());

                return request;
            }

            @Override
            public QueryItemResult doProcess(QueryItemRequest request) {
                return itemFacade.query(request);
            }
        });
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateItemResult> update(@RequestHeader HttpHeaders headers, @RequestBody UpdateItemForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<UpdateItemResult, UpdateItemRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public UpdateItemRequest composeRequest() {
                UpdateItemRequest request = new UpdateItemRequest();
                request.setItemId(form.getItemId());
                request.setItemUpdateContext(form.getItemUpdateContext());

                return request;
            }

            @Override
            public UpdateItemResult doProcess(UpdateItemRequest request) {
                return itemFacade.update(request);
            }
        });
    }

    @PostMapping("/recommend")
    public ResponseEntity<RecommendResult> recommend(@RequestHeader HttpHeaders headers, @RequestBody RecommendForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<RecommendResult, RecommendRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public RecommendRequest composeRequest() {
                return new RecommendRequest();
            }

            @Override
            public RecommendResult doProcess(RecommendRequest request) {
                return itemFacade.recommend(request);
            }
        });
    }

    @PostMapping("/autocomplete")
    public ResponseEntity<AutocompleteItemResult> autocomplete(@RequestHeader HttpHeaders headers, @RequestBody ItemAutocompleteForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<AutocompleteItemResult, AutocompleteItemRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public AutocompleteItemRequest composeRequest() {
                AutocompleteItemRequest request = new AutocompleteItemRequest();
                request.setAutocomplete(form.getAutocomplete());

                return request;
            }

            @Override
            public AutocompleteItemResult doProcess(AutocompleteItemRequest request) {
                return itemFacade.autocomplete(request);
            }
        });
    }
}
