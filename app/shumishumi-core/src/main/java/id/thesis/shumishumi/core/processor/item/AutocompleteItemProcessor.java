package id.thesis.shumishumi.core.processor.item;

import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.core.service.ItemService;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.item.AutocompleteItemRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.item.AutocompleteItemResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

public class AutocompleteItemProcessor implements BaseProcessor {

    @Autowired
    private ItemService itemService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        AutocompleteItemRequest request = (AutocompleteItemRequest) baseRequest;
        AutocompleteItemResult result = (AutocompleteItemResult) baseResult;

        if (request.getAutocomplete().length() < 3) {
            return;
        }

        List<String> itemName = itemService.autocomplete(request.getAutocomplete(), true);
        result.setItemName(new HashSet<>(itemName));
    }
}
