package id.thesis.shumishumi.web.controller;

import id.thesis.shumishumi.common.model.form.item.*;
import id.thesis.shumishumi.core.callback.ControllerCallback;
import id.thesis.shumishumi.core.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.facade.api.ItemFacade;
import id.thesis.shumishumi.facade.request.item.*;
import id.thesis.shumishumi.facade.result.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                request.setItemImages(form.getItemImages());
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

    @PostMapping("/query/detail")
    public ResponseEntity<QueryItemDetailResult> queryDetail(@RequestHeader HttpHeaders headers, @RequestBody QueryItemDetailForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<QueryItemDetailResult, QueryItemDetailRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public QueryItemDetailRequest composeRequest() {
                QueryItemDetailRequest request = new QueryItemDetailRequest();
                request.setItemId(form.getItemId());

                return request;
            }

            @Override
            public QueryItemDetailResult doProcess(QueryItemDetailRequest request) {
                return itemFacade.queryDetail(request);
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
