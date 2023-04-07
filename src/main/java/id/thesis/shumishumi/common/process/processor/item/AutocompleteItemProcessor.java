package id.thesis.shumishumi.common.process.processor.item;

import id.thesis.shumishumi.common.process.processor.BaseProcessor;
import id.thesis.shumishumi.core.service.ItemService;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.item.AutocompleteItemRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.result.item.AutocompleteItemResult;
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
