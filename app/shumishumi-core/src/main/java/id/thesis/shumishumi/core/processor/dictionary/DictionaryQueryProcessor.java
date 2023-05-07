package id.thesis.shumishumi.core.processor.dictionary;

import id.thesis.shumishumi.common.service.HobbyService;
import id.thesis.shumishumi.common.service.InterestLevelService;
import id.thesis.shumishumi.common.service.ItemCategoryService;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.dictionary.DictionaryQueryRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.dictionary.DictionaryQueryResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DictionaryQueryProcessor implements BaseProcessor {

    @Autowired
    private InterestLevelService interestLevelService;

    @Autowired
    private ItemCategoryService itemCategoryService;

    @Autowired
    private HobbyService hobbyService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        DictionaryQueryRequest request = (DictionaryQueryRequest) baseRequest;
        DictionaryQueryResult result = (DictionaryQueryResult) baseResult;

        List<String> dictionaries = new ArrayList<>();
        switch (request.getDictionaryKey()) {
            case "INTEREST_LEVEL":
                interestLevelService.queryAll().forEach(level ->
                        dictionaries.add(level.getInterestLevelName()));
                break;

            case "HOBBY":
                hobbyService.queryAll().forEach(hobby ->
                        dictionaries.add(hobby.getHobbyName()));
                break;

            case "CATEGORY":
                itemCategoryService.queryAll().forEach(category ->
                        dictionaries.add(category.getCategoryName()));
                break;

            default:
                break;
        }

        result.setDictionaries(dictionaries);
    }
}
